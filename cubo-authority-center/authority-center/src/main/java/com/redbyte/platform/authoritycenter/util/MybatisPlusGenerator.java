package com.redbyte.platform.authoritycenter.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        String[] tables = {"user"};

        String modulePackageName = "cubo-authority-center/authority-center";

        GlobalConfig globalConfig = new GlobalConfig();

        String projectPath = System.getProperty("user.dir") + "/" + modulePackageName;
        System.out.println(projectPath);

        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("wangwq");
        globalConfig.setOpen(false);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/cubo?autoReconnect=true&useUnicode=true&createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.redbyte.platform.authoritycenter.core");
//        packageConfig.setModuleName("entity");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("dao");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController(null);
//        packageConfig.set();

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(tables);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setLogicDeleteFieldName("deleted");


        AutoGenerator autoGenerator = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .setCfg(new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig() {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                })));

        autoGenerator.execute();
    }
}
