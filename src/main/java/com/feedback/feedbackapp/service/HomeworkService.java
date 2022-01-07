package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.exception.InformationNotFoundException;
import com.feedback.feedbackapp.model.Homework;
import com.feedback.feedbackapp.repository.HomeworkRepository;
import com.feedback.feedbackapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class HomeworkService {

    private HomeworkRepository homeworkRepository;
    private static final Logger LOGGER = Logger.getLogger(HomeworkService.class.getName());

    @Autowired
    public void setHomeworkRepository(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> getHomework(){
        LOGGER.info("calling getHomework method from service");
        return homeworkRepository.findAll();
    }

    public Optional getHomework(Long homeworkId) {
        LOGGER.info("calling getHomework method from service");
        Optional homework = homeworkRepository.findById(homeworkId);
            if (homework.isEmpty()) {
                throw new InformationNotFoundException("homework with id: " + homeworkId+
                        " not found");}
            return homework;
    }

    public Homework createHomework(Homework homeworkObject) {
        LOGGER.info("calling createHomework method from service");

        MyUserDetails userDetails =
                (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user ---");
        }
        Optional<Homework> homework  = homeworkRepository.findByName(homeworkObject.getName());
        if (homework.isPresent()) {
            throw new InformationExistException("homework with topic " + homework.get().getName()
                        + " " + "already exists");
        }
        // todo : check for validations of homeworkobject
        homeworkObject.setUser(userDetails.getUser());
        return homeworkRepository.save(homeworkObject);
    }

    public Homework updateHomework(Long homeworkId, Homework homeworkObject) {
        LOGGER.info("calling updateHomework method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user ---");
        }

        Optional<Homework> homework = homeworkRepository.findById(homeworkId);
        if (homework.isEmpty()) {
            throw new InformationNotFoundException("homework with id " + homeworkId + " don't exists");
        }

        if (homeworkObject.getDueDate()!= null)
                homework.get().setDueDate(homeworkObject.getDueDate());
        if (homeworkObject.getName() != null) homework.get().setName(homeworkObject.getName());
        return homeworkRepository.save(homework.get());
    }

    public Optional<Homework> deleteHomework(Long homeworkId) {
            LOGGER.info("calling deleteHomework method from service");

            MyUserDetails userDetails =
                    (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userRole = userDetails.getUser().getRole().toLowerCase();
            if (!userRole.equals("instructor")) {
                throw new InformationNotFoundException("-------not a valid user ---");
            }
            Optional<Homework> homework = homeworkRepository.findById(homeworkId);
            if (homework.isEmpty()) {
                throw new InformationNotFoundException("homework with id : " + homeworkId + " not found");
            }
             homeworkRepository.deleteById(homeworkId);
        return homework;
    }
}
