package cn.net.derbi.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * @author rookie 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("WangMj");
        globalConfig.setOpen(false);
        globalConfig.setXmlName("%sMapper.xml");
        globalConfig.setBaseResultMap(true);
        globalConfig.setFileOverride(true);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置 需配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://39.105.81.213:3306/derbi?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        autoGenerator.setDataSource(dataSourceConfig);
        String[] tables = {"admin_login_log","user_menu","user_resource","user_resource_category",
        "user_role_menu_relation","user_role_resource_relation"};

        // 生成包配置
        PackageConfig packageConfig = new PackageConfig();

        //如果需要手动输入模块名
        //packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setModuleName("dev");
        packageConfig.setParent("cn.net.derbi");
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //return null;

                // 自定义输出文件名
                return projectPath + "/src/main/java/com/derbi/dev/mapper/xml"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(tables);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
               // setSuperEntityClass(".BaseEntity");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true)
                .setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // strategyConfig.setSuperControllerClass(".BaseController");

        // 如果 setInclude() 不加参数, 会自定查找所有表
        // 如需要制定单个表, 需填写参数如: strategyConfig.setInclude("user_info);
        // strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setControllerMappingHyphenStyle(true);

        //自动将数据库中表名为 user_info 格式的专为 UserInfo 命名
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");
        //自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategyConfig.setTableFillList(tableFills);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        System.out.println("===================== MyBatis Plus Generator ==================");

        autoGenerator.execute();

        System.out.println("================= MyBatis Plus Generator Execute Complete ==================");
    }

}
