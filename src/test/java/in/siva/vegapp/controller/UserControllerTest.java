//package in.siva.vegapp.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import in.siva.vegapp.dto.UserInfo;
//import in.siva.vegapp.exception.EmailAlreadyExistException;
//import in.siva.vegapp.exception.InvalidLoginException;
//import in.siva.vegapp.exception.MobileNumberAlreadyExistException;
//import in.siva.vegapp.exception.UsernameAlreadyExistException;
//import in.siva.vegapp.model.User;
//import in.siva.vegapp.service.UserService;
//
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//class UserControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private UserService userService;
//
//	@InjectMocks
//	UserController userController;
//
//	@Before
//	void setUp() {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	/**
//	 * This method is used to test login in controller layer. Service layer response
//	 * is mocked and controller layer of login is validated. This method is
//	 * validated when valid details is provided from front end
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	void validLoginTest() throws Exception {
//		User userObj = new User();
//		userObj.setUsername("sivass");
//		userObj.setPassword("Siva123@");
//
//		UserInfo userInfo = new UserInfo();
//		userInfo.setRole("C");
//		userInfo.setUserId(10);
//		String userJson = new ObjectMapper().writeValueAsString(userObj);
//		when(userService.loginValidation(any(String.class), any(String.class))).thenReturn(userInfo);
//		mockMvc.perform(post("/vegapp/v1/users/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.role").value("C"))
//				.andExpect(jsonPath("$.userId").value(10));
//	}
//
//	/**
//	 * This method is used to test login in controller layer. Service layer response
//	 * is mocked and controller layer of login is validated. This method is
//	 * validated when invalid details is provided from front end
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	void invalidLoginTest() throws Exception {
//		User userObj = new User();
//		userObj.setUsername("sivass");
//		userObj.setPassword("Sivas23@");
//		String userJson = new ObjectMapper().writeValueAsString(userObj);
//		when(userService.loginValidation(any(String.class), any(String.class)))
//				.thenThrow(new InvalidLoginException("Invalid Login Credentials"));
//		mockMvc.perform(post("/vegapp/v1/users/login").contentType(MediaType.APPLICATION_JSON).content(userJson))
//				.andExpect(status().isUnauthorized())
//				.andExpect(jsonPath("$.errorMessage").value("Invalid Login Credentials"));
//	}
//
//	/**
//	 * This method is used to test register in controller layer. Service layer
//	 * response is mocked and controller layer of register is validated. This method
//	 * is validated when valid details is provided from front end
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	void validRegisterTest() throws Exception {
//		User userObj = new User();
//		userObj.setName("Siva Murugan");
//		userObj.setEmail("valid@gmail.com");
//		userObj.setGender("M");
//		userObj.setMobileNumber(9878909878L);
//		userObj.setRole("C");
//		userObj.setUsername("sivanew");
//		userObj.setPassword("Siva123@");
//		String userJson = new ObjectMapper().writeValueAsString(userObj);
//		when(userService.registerUser(any(User.class))).thenReturn(true);
//		mockMvc.perform(post("/vegapp/v1/users/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
//				.andExpect(status().isOk()).andExpect(content().string("true"));
//	}
//
//	/**
//	 * This method is used to test register in controller layer. Service layer
//	 * response is mocked and controller layer of register is validated. This method
//	 * is validated when email already used by a user and service layer throws
//	 * exception of EmailAlreadyExistException.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	void repeatedEmailTest() throws Exception {
//		User userObj = new User();
//		userObj.setName("Siva Murugan");
//		userObj.setEmail("valid@gmail.com");
//		userObj.setGender("M");
//		userObj.setMobileNumber(9878909878L);
//		userObj.setRole("C");
//		userObj.setUsername("sivanew");
//		userObj.setPassword("Siva123@");
//		String userJson = new ObjectMapper().writeValueAsString(userObj);
//		when(userService.registerUser(any(User.class))).thenThrow(new EmailAlreadyExistException("E_ER01"));
//		mockMvc.perform(post("/vegapp/v1/users/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
//				.andExpect(status().isConflict()).andExpect(jsonPath("$.errorMessage").value("E_ER01"));
//	}
//
//	/**
//	 * This method is used to test register in controller layer. Service layer
//	 * response is mocked and controller layer of register is validated. This method
//	 * is validated when username already used by a user and service layer throws
//	 * exception of UsernameAlreadyExistException.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	void repeatedUsernameTest() throws Exception {
//		User userObj = new User();
//		userObj.setName("Siva Murugan");
//		userObj.setEmail("valid@gmail.com");
//		userObj.setGender("M");
//		userObj.setMobileNumber(9878909878L);
//		userObj.setRole("C");
//		userObj.setUsername("sivanew");
//		userObj.setPassword("Siva123@");
//		String userJson = new ObjectMapper().writeValueAsString(userObj);
//		when(userService.registerUser(any(User.class))).thenThrow(new UsernameAlreadyExistException("E_UR01"));
//		mockMvc.perform(post("/vegapp/v1/users/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
//				.andExpect(status().isConflict()).andExpect(jsonPath("$.errorMessage").value("E_UR01"));
//	}
//
//	/**
//	 * This method is used to test register in controller layer. Service layer
//	 * response is mocked and controller layer of register is validated. This method
//	 * is validated when mobile number already used by a user and service layer
//	 * throws exception of MobileNumberAlreadyExistException.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	void repeatedMobileNumberTest() throws Exception {
//		User userObj = new User();
//		userObj.setName("Siva Murugan");
//		userObj.setEmail("valid@gmail.com");
//		userObj.setGender("M");
//		userObj.setMobileNumber(9878909878L);
//		userObj.setRole("C");
//		userObj.setUsername("sivanew");
//		userObj.setPassword("Siva123@");
//		String userJson = new ObjectMapper().writeValueAsString(userObj);
//		when(userService.registerUser(any(User.class))).thenThrow(new MobileNumberAlreadyExistException("E_MR01"));
//		mockMvc.perform(post("/vegapp/v1/users/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
//				.andExpect(status().isConflict()).andExpect(jsonPath("$.errorMessage").value("E_MR01"));
//	}
//}
