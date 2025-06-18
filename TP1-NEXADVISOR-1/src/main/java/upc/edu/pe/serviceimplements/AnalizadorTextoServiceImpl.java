package upc.edu.pe.serviceimplements;




import org.apache.tika.Tika;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AnalizadorTextoServiceImpl {
	

    private final String apiUrl = "https://api.languagetool.org/v2/check";

    public String analizarTexto(String texto) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", texto);
            params.add("language", "es");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Error al analizar el texto\"}";
        }
    }

    public String extraerTexto(MultipartFile archivo) {
        try {
            Tika tika = new Tika();
            return tika.parseToString(archivo.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al extraer el texto del archivo.";
        }
    }

}


