package com.feedback.feedbackapp.controller;
import com.feedback.feedbackapp.model.Homework;
import com.feedback.feedbackapp.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class HomeworkController {

    private HomeworkService homeworkService;
    private static final Logger LOGGER = Logger.getLogger(HomeworkService.class.getName());

    @Autowired
    public void setHomeworkService(HomeworkService homeworkService){
        this.homeworkService = homeworkService;
    }

    @GetMapping("/homework")
    public List<Homework> getHomework(){
        LOGGER.info("This instance should be retrieving all the Homework");
        return homeworkService.getHomework();
    }

    @GetMapping(path = "/homework/{homeworkId}")
    public Optional getHomework(@PathVariable Long homeworkId){
            LOGGER.info("This instance should be retrieving a Homework data");
            return homeworkService.getHomework(homeworkId);
    }

    @PostMapping(path = "/homework")
    public Homework createHomework(@RequestBody Homework homeworkObject){
        LOGGER.info("This instance should be creating a Homework");
        return homeworkService.createHomework(homeworkObject);
    }

    @PutMapping(path = "/homework/{homeworkId}")
    public Homework updateHomework(@PathVariable(value = "homeworkId") Long homeworkId,
                                   @RequestBody Homework homeworkObject){
        LOGGER.info("This instance should be updating Homework method");
        return homeworkService.updateHomework(homeworkId, homeworkObject);
        }


        @DeleteMapping(path = "/homework/{homeworkId}")
        public Optional <Homework> deleteHomework(@PathVariable(value = "homeworkId") Long homeworkId) {
            LOGGER.info("This instance should be deleting a homework");
            return homeworkService.deleteHomework(homeworkId);
        }
}




