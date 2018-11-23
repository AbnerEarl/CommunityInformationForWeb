package com.zhang.shequ.base.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 	代码生成器请勿轻易操作
 * 	操作时请取消 pom.xml 中的 velocity 注释
 * </p>
 */
public class MpGenerator {

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:/TemporaryDirectory");//生成存放地址
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("ZhangGang");
        
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);
        
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/shequ?characterEncoding=utf8");
        mpg.setDataSource(dsc);
        
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] {"sys_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"sys_farm","sys_farm_order","sys_farm_product","sys_order_record"});//生成指定表
        // 自定义 mapper 父类
        //strategy.setSuperMapperClass("com.zhang.shequ.base.mapper.BaseMapper");
        // 自定义 service 父类
        //strategy.setSuperServiceClass("com.zhang.shequ.base.service.BaseService");
        // 自定义 service 实现类父类
        //strategy.setSuperServiceImplClass("com.zhang.shequ.base.service.impl.BaseServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("com.zhang.shequ.base.controller.BaseController");
        mpg.setStrategy(strategy);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zhang.shequ");
        pc.setMapper("core.mapper");
        pc.setXml("core.mapper.mapping");
        pc.setEntity("core.entity");
        pc.setService("core.service");
        pc.setServiceImpl("core.service.impl");
        pc.setController("modular.business.controller");
        mpg.setPackageInfo(pc);
        
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        /*
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);
        */

        
        // 执行生成
        mpg.execute();
        // 打印注入设置
        //System.err.println(mpg.getCfg().getMap().get("abc"));
	}

}

