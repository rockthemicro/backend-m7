package mihnea.licenta.server.controller;

import mihnea.licenta.server.entity.SensorsData;
import mihnea.licenta.server.entity.SensorsDataRepository;
import mihnea.licenta.server.entity.User;
import mihnea.licenta.server.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ServerLogic {

    @Autowired
    private SensorsDataRepository sdr;

    /*
     * AutoWired se ocupa de crearea si asignarea automata a unei instante dintr-o clasa potrivita,
     * pentru o anumita variabila (ur) de tip interfata
     */
    @Autowired
    private UserRepository ur;

    @GetMapping("/salut")
    public String greeting(@RequestParam(value = "altparam", defaultValue = "ce sugi ma?") String costasu) {
        return costasu;
    }

    @GetMapping("/sendData")
    public String sendData(@RequestParam(value = "temp") double temp,
                           @RequestParam(value = "earthHum") double earthHum,
                           @RequestParam(value = "light") double light) {

        SensorsData sd = new SensorsData();
        sd.setTemp(temp);
        sd.setEarthHum(earthHum);
        sd.setLight(light);
        sd.setDate(LocalDate.now());

        sd = sdr.save(sd);
        if (sd != null)
            return "OK";
        else
            return "FAIL";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "userName") String userName,
                        @RequestParam(value = "password") String password) {

        Optional<User> user = ur.findById(userName);
        if (user.isPresent()) {

            if (user.get().getPassword().equals(password)) {
                return "OK";
            } else {
                return "FAIL";
            }
        } else {
            return "FAIL";
        }
    }

    @GetMapping("/signup")
    public String signup(@RequestParam(value = "userName") String userName,
                         @RequestParam(value = "password") String password) {

        /* verificam daca userul pe care vrem sa-l creem este deja existent */
        Optional<User> user = ur.findById(userName);
        if (user.isPresent()) {
            /* daca exista, FAIL */
            return "FAIL";
        }

        /* creare si configurare user */
        User u = new User();
        u.setUserName(userName);
        u.setPassword(password);
        /* salvare user in baza de date */
        u = ur.save(u);

        if (u != null)
            return "OK";
        else
            return "FAIL";
    }

    @GetMapping("/getDataSince")
    public SensorsData[] getDataSince(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        List<SensorsData> dataList = sdr.findAllByDateAfter(date);
        SensorsData[] result = new SensorsData[dataList.size()];

        int i = 0;
        for (var sd : dataList) {
            result[i++] = sd;
        }

        return result;
    }
}
