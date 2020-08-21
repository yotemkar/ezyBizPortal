package com.hssa.ezybiz.services;

import com.hssa.ezybiz.dto.ApplicationToken;

public interface ApplicationTokenBO {

	int validateApplicationTokenAndURL(ApplicationToken tokenDetails);
}
