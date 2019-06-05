import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;




@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jsons
{
    private String id;
    private String name;
    private String desc;
    private String type;
    private List<Templete> template;




}







