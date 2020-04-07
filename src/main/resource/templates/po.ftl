package ${packageName};

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;
@Data
@Accessors(chain = true)
public class ${poName}{
<#list tableCols as p>

private ${p.javaType} ${p.javaColName};

</#list>
}