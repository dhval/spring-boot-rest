package rest;

/**
 * Created by dhval on 3/30/15.
 */

import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private PersonRepository repository;

    @Autowired
    HttpServletRequest request;


    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/hello")
    public Status hello() {
        return new Status("Connected", "Hello !");
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Status persons(@RequestParam(value = "sort", defaultValue = "name") String sortBy,
                          @RequestParam(value = "start", defaultValue = "0") String start,
                          @RequestParam(value = "size", defaultValue = "5") String size)
            throws AppException {
        Integer startInt = (StringUtils.isNumeric(start)) ? Integer.parseInt(start) : 0;
        Integer sizeInt = (StringUtils.isNumeric(size)) ? Integer.parseInt(size) : 0;
        Long count = repository.count();
        if (startInt < 0 || sizeInt <= 0 || startInt >= count)
            throw new AppException("Invalid start or size requested");
        if (sizeInt > 10)
            throw new AppException("Cannot serve more than 10 records in a single request");
        final PageRequest page = new PageRequest(startInt*sizeInt, sizeInt, Sort.Direction.DESC, sortBy);
        List<Person> persons = repository.findAll(page).getContent();
        return new Status(Long.toString(count), "success", persons);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Status create(@RequestBody Person person,
                         @RequestHeader("user-agent") String agent) throws AppException {
        person.setAgent(agent);
        person.setAddress(request.getRemoteAddr());
        if (person.getId() != null && person.getId() >0) {
                person.setModifyDate(Application.toTime(new Date()));
        }
        repository.save(person);
        return new Status("0", "success", person);
    }

    @ExceptionHandler(AppException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleException(AppException exception) {
        LOG.warn(exception.getMessage());
        return newDefaultErrorMessage(exception.getContent());
    }


    private static Map<String, Object> newDefaultErrorMessage(String msg) {
        if (StringUtils.isEmpty(msg))
            msg ="An internal server error occurred while processing your request.";
        Map<String, Object> message = new HashMap<>();
        message.put("title", "An error has occurred");
        message.put("content", msg);
        return message;
    }
}