package com.preparazione.preparazione.config;
import com.preparazione.preparazione.entities.WebsiteUser;
import com.preparazione.preparazione.repo.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {

    @Autowired
    private WebsiteUserRepository websiteUserRepository;

    @Autowired
    private WebsiteUser websiteUser;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ItemReader<WebsiteUser> websiteUserItemReader;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public WebsiteUser websiteUser(){
        return new WebsiteUser();
    }

    @Bean
    public Job importWebsiteUserJob (){
        return new JobBuilder("importWebsiteUserJob", jobRepository)
                .start(websiteUserStep(jobRepository, websiteUser,transactionManager))
                .build();
    }

    @Bean
    private Step websiteUserStep(JobRepository jobRepository, WebsiteUser websiteUser, PlatformTransactionManager transactionManager) {
        return new StepBuilder("importWebsiteUserStep", jobRepository)
                .<WebsiteUser, WebsiteUser>chunk(100, transactionManager)
                .reader(websiteUserItemReader)
                .writer(writer())
                .build();
    }
    @Bean
    private ItemWriter<WebsiteUser> writer() {
        return websiteUserRepository::saveAll;
    }

    @Bean
    public FlatFileItemReader<WebsiteUser> flatFileItemReader(@Value("${inputFile}") Resource inputFile){
        FlatFileItemReader<WebsiteUser> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("csvReader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(inputFile);
        flatFileItemReader.setLineMapper(linMappe());
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<WebsiteUser> linMappe() {
        DefaultLineMapper<WebsiteUser> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("name","email");
        lineTokenizer.setStrict(false); // Set strict property to false
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(WebsiteUser.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

}
