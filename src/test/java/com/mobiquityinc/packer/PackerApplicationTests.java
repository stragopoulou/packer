package com.mobiquityinc.packer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackerApplicationTests {

  private String firstCasePath, weightMore100CasePath, itemsMore15CasePath,
      itemCostMore100CasePath, itemWeightMore100CasePath, allCasesPath;

  @Test(expected = APIException.class)
  public void testPackerNullFilepath() {
    Packer.pack(null);
  }

  @Test(expected = APIException.class)
  public void testPackerEmptyFilepath() {
    Packer.pack("");
  }

  @Test
  public void testPackerFirstTestCase() {
    firstCasePath = "C:\\Users\\SpyridoulaTragopoulo\\IdeaProjects\\packer\\src\\test\\java\\com\\mobiquityinc\\packer\\resources\\first_test_case.txt";
    assertThat(Packer.pack(firstCasePath), is(equalTo("4")));
  }


  @Test(expected = APIException.class)
  public void testPackerWeightMoreThan100() {
    weightMore100CasePath = "C:\\Users\\SpyridoulaTragopoulo\\IdeaProjects\\packer\\src\\test\\java\\com\\mobiquityinc\\packer\\resources\\weight_more_100_test_case.txt";
    Packer.pack(weightMore100CasePath);
  }

  @Test(expected = APIException.class)
  public void testPackerItemsMoreThan15() {
    itemsMore15CasePath = "C:\\Users\\SpyridoulaTragopoulo\\IdeaProjects\\packer\\src\\test\\java\\com\\mobiquityinc\\packer\\resources\\items_more_15_test_case.txt";
    Packer.pack(itemsMore15CasePath);
  }

  @Test(expected = APIException.class)
  public void testPackerItemCostMoreThan100() {
    itemCostMore100CasePath = "C:\\Users\\SpyridoulaTragopoulo\\IdeaProjects\\packer\\src\\test\\java\\com\\mobiquityinc\\packer\\resources\\item_max_cost_test_case.txt";
    Packer.pack(itemCostMore100CasePath);
  }

  @Test(expected = APIException.class)
  public void testPackerItemWeightMoreThan100() {
    itemWeightMore100CasePath = "C:\\Users\\SpyridoulaTragopoulo\\IdeaProjects\\packer\\src\\test\\java\\com\\mobiquityinc\\packer\\resources\\item_max_weight_test_case.txt";
    Packer.pack(itemWeightMore100CasePath);
  }

  @Test
  public void testPackerAllTestCases() {
    allCasesPath = "C:\\Users\\SpyridoulaTragopoulo\\IdeaProjects\\packer\\src\\test\\java\\com\\mobiquityinc\\packer\\resources\\all_test_cases.txt";
    assertThat(Packer.pack(allCasesPath), is(equalTo("4\n"+ "-\n"+ "2,7\n"+ "8,9")));
  }





}
