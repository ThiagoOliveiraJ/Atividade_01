
import com.example.clima.dto.ClimaResponse;

@RestController
public class ClimaController {
    @GetMapping("/clima")
    public ClimaResponse consultarClima(){
        return new ClimaResponse(
            "Belo Horizonte",
            "MG",
            25.0,
            10.5,
            180,
            28.0,
            18.0,
            "2026-08-26T"            
        );
    }    
}
