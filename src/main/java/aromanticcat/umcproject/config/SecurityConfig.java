package aromanticcat.umcproject.config;

import aromanticcat.umcproject.jwt.JwtAuthenticationFilter;
import aromanticcat.umcproject.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .formLogin().disable() // 시큘티에서 지원하는 페이지 뜨지 않도록. rest api로 개발 할 것이기 떄문
                .httpBasic().disable()
                .cors().disable() // 프론트와 백엔드 협업 시 필요함. 나중에 disable() 제거하기
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 말고 jwt 사용할거니까 stateless.
                .and()
                .authorizeRequests()
                    .antMatchers("/","/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**",
                        "/member/join", "/member/login").permitAll()
                .antMatchers("/member").hasRole("USER") // 이 경로에 대해서는 USER 권한만 접근 가능
                .antMatchers("/store/upload").hasRole("ADMIN") // 이 경로에 대해서는 USER 권한만 접근 가능
                    .anyRequest().authenticated()  // 나머지 요청들은 다 인증을 하겠다는 뜻
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
