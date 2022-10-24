package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan ( // @component 붙은 것을 스프링 빈으로 등록
        basePackages = "hello.core",
        excludeFilters=@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {

    //@Bean(name = "memoryMemberRepository")
    //MemoryMemberRepository memoryMemberRepository(){
    //return new MemoryMemberRepository();
    //}
}
