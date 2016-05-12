/*
 * Copyright 2016 Mindfire Solutions
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.model.Person;
import com.test.service.UserService;

@RestController
public class DataTableRestController {

	@Autowired
	private UserService userService;

	/**
	 * This method is used to map requests for fetching all user's details.
	 * 
	 * @param input
	 *            {@link DataTablesInput} object
	 * @return {@link User} DataTable
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = { "/data/userList" }, method = RequestMethod.GET)
	public DataTablesOutput<Person> userList(@Valid DataTablesInput input) {
		return userService.findAllUsers(input);
	}
}
