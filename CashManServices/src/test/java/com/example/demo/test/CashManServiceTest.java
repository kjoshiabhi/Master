package com.example.demo.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.CashManServicesApplicationTests;

import net.minidev.json.JSONObject;

public class CashManServiceTest extends CashManServicesApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * Junit test case for getTotalAmount
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void getTotalAmountTest() throws Exception {

		this.mockMvc.perform(get("/api/cash/getCash")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.data").exists());

	}

	/**
	 * Junit test case for addCash
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void addCashTest() throws Exception {
		JSONObject noteData = new JSONObject();

		noteData.put("note", "TWENTY");
		noteData.put("currentNotes", "20");

		this.mockMvc
				.perform(post("/api/cash/addCash").contentType("application/json;charset=UTF-8")
						.content(TestUtil.convertObjectToJsonString(noteData)))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

	}

}
