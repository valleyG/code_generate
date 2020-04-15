package ${packageName};

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;
@Data
@Accessors(chain = true)
@ApiModel("")
public class ${poName} extends BasePO{
<#list tableCols as p>
@ApiModelProperty("${p.comment}")
private ${p.javaType} ${p.javaColName};

</#list>
}