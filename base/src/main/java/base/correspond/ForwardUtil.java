package base.correspond;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ForwardUtil {
    /**
     * 不允许Post请求中有同名Key值
     * 仅支持请求中key - value 键值对形式的参数，以String-String Map形式返回
     * @param request
     * @return
     */
    public static MultiValueMap<String,String> getKeyValueMapForParams (HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String,String> ret = new LinkedMultiValueMap<>();
        for (String key : parameterMap.keySet()) {
            ret.add(key,parameterMap.get(key)[0]);
        }
        return ret;
    }

    public static MultiValueMap<String,Object> getKeyValueMapForOneParam (String key, Object value) {
        MultiValueMap<String,Object> ret = new LinkedMultiValueMap<>();
        ret.add(key,value);
        return ret;
    }

    public static HttpEntity getHttpEntityForFile (String key, CommonsMultipartFile file) {
        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }

            @Override
            public long contentLength() {
                return file.getSize();
            }
        };
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add(key, fileAsResource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(data, headers);
        return requestEntity;
    }
}
