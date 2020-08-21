package com.hssa.ezybiz.dao;

import com.hssa.ezybiz.dto.ApplicationToken;

public interface ApplicationTokenDAO {

	int validateApplicationTokenAndURL(ApplicationToken tokenDetails);
}
