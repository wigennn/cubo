package com.redbyte.platform.authoritycenter.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        String[] tables = {"login_log"};

        String modulePackageName = "cubo-authority-center/authority-center";

        String projectPath = System.getProperty("user.dir") + "/" + modulePackageName;
        System.out.println(projectPath);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("wangwq");
        globalConfig.setOpen(false);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setFileOverride(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/cubo?autoReconnect=true&useUnicode=true&createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.redbyte.platform.authoritycenter.core");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("dao");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController(null);
        packageConfig.setXml(null);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(tables);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);

        AutoGenerator autoGenerator = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setTemplate(new TemplateConfig().setXml(null).setController(null))
                .setCfg(cfg);

        autoGenerator.execute();
    }
}
