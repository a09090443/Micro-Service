package com.zipe.test.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zipe.model.PersonalTitle;
import com.zipe.repository.IPersonalTitleRepository;
import com.zipe.test.base.TestBase;

public class PersonalTitleRepositoryTest extends TestBase {

	@Autowired
	private IPersonalTitleRepository personalTitleRepository;

	@Ignore
	@Test
	public void testFindAll() {
		List<PersonalTitle> personalTitleList = personalTitleRepository.findAll();
		assertNotNull(personalTitleList);
	}

	// @Ignore
	@Test
	public void testFindByTitleId() {
		Set<PersonalTitle> personalTitleList = personalTitleRepository.findPersonalTitleByTitleIdIn(Arrays.asList("01,02".split(",")));
		assertNotNull(personalTitleList);
	}

}
