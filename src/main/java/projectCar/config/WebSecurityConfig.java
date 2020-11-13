package projectCar.config;

//@Configuration
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Qualifier("userServiceImpl")
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/registration","/res/css/**", "/res/js/**")
//                .not().fullyAuthenticated().antMatchers("/","/**").authenticated().and();
//
//        http.formLogin().loginPage("/login")
//                .loginProcessingUrl("/login-check").failureUrl("/error")
//                .usernameParameter("check_username").passwordParameter("check_password")
//                .defaultSuccessUrl("/").permitAll();
//
//        http.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
//                .and();
//    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder authentication) throws Exception{
//        authentication.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//
//    }
//
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//
//}
