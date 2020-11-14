package projectCar.config;

//
//@Configuration
//@ComponentScan(basePackages = "projectCar")
//@EnableTransactionManagement
//@PropertySource(value = {"classpath:database.properties"})
public class HibernateConfig {

//    private Environment environment;
//
//    @Autowired
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect",
//                environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql",
//                environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.hbm2ddl.auto",
//                environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.search.default.directory_provider",
//                environment.getRequiredProperty("hibernate.search.default.directory_provider"));
//        properties.put("hibernate.search.default.indexBase",
//                environment.getRequiredProperty("hibernate.search.default.indexBase"));
//        return properties;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("projectCar.entity");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

}
