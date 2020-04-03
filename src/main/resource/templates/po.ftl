package ${packageName};

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class ${poName}{
<#list tableCols as p>

private ${p.javaType} ${p.javaColName};

</#list>
}