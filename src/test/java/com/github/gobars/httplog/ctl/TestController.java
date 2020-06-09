package com.github.gobars.httplog.ctl;

import com.github.gobars.httplog.HttpLog;
import com.github.gobars.httplog.dto.TestDto;
import com.github.gobars.httplog.ex.TestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

  /**
   * test get.
   *
   * @param id id
   * @return id
   */
  @HttpLog(tables = "biz_log", fix = "desc:ID查找", eager = true)
  @GetMapping(value = "/{id}")
  public String get(@PathVariable Integer id) {
    return "test id : " + id;
  }

  /**
   * test post.
   *
   * @param testDto testDto
   * @return testDto
   */
  @HttpLog(tables = "biz_log_post")
  @PostMapping
  public TestDto post(@RequestBody TestDto testDto) {
    return testDto;
  }

  /**
   * test put error.
   *
   * @param testDto testDto
   */
  @PutMapping
  public void error(@RequestBody TestDto testDto) {
    log.warn("error TestException will be thrown");
    throw new TestException(testDto.toString());
  }
}
