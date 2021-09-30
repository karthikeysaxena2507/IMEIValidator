package IMEIValidator.resource;

import IMEIValidator.service.IMEIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://imei-validator.netlify.app/")
@RequestMapping("/api")
public class IMEIResource {

    private final IMEIService imeiService;

    @Autowired
    public IMEIResource(IMEIService imeiService) {
        this.imeiService = imeiService;
    }

    @PostMapping
    public ResponseEntity<String> checkIMEI(@RequestBody String imeiCode) {
        return new ResponseEntity<>(imeiService.checkIMEI(imeiCode), HttpStatus.OK);
    }

}
