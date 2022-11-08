package umc.crud.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.crud.info.model.City;
import umc.crud.info.model.Project;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class InfoController {

    private InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/info")
    public Object projectInfo() {
        Project project = infoService.getProjectInfo();
        return project;
    }

    @GetMapping("/cityList")
    public Object cityList() {
        log.debug("/cityList start");
        List<City> cityList = infoService.getCityList();

        return cityList;
    }
}
