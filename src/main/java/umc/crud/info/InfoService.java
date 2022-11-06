package umc.crud.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.crud.info.model.City;
import umc.crud.info.model.Project;
import umc.crud.info.repository.CityRepository;

import java.util.Date;
import java.util.List;

@Service
public class InfoService {

    private final CityRepository cityRepository;

    @Autowired
    public InfoService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Project getProjectInfo() {

        Project project = new Project();
        project.projectName = "crud";
        project.author = "crudadmin";
        project.createdDate = new Date();

        return project;
    }

    public List<City> getCityList() {
        return this.cityRepository.findList();
    }
}
