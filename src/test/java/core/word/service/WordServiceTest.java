package core.word.service;

import com.crm.core.words.entity.Word;
import com.crm.core.words.service.WordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class WordServiceTest{

    @Autowired
    private WordService wordService;

    @Test
    public void save(){
        Word word = new Word();
        word.setContent("你妈逼");

        wordService.save(word);
    }
}
