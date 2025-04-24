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
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {

    @Autowired
    private WebsiteUserRepository websiteUserRepository;


    @Bean
    public JobRepository jobRepository(DataSource dataSource, TransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager((PlatformTransactionManager) transactionManager);
        factory.setDatabaseType("SQLSERVER");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public Job importWebsiteUserJob (Step websiteUserStep, JobRepository jobRepository){
        return new JobBuilder("importWebsiteUserJob", jobRepository)
                .start(websiteUserStep)
                .build();
    }

    @Bean
    public Step websiteUserStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, FlatFileItemReader<WebsiteUser> reader) {
        return new StepBuilder("importWebsiteUserStep", jobRepository)
                .<WebsiteUser, WebsiteUser>chunk(100, transactionManager)
                .reader(reader)
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<WebsiteUser> writer() {
        return websiteUserRepository::saveAll;
    }

    @Bean
    public FlatFileItemReader<WebsiteUser> flatFileItemReader(){
        FlatFileItemReader<WebsiteUser> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("csvReader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(new ClassPathResource("boh-data.csv"));
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
