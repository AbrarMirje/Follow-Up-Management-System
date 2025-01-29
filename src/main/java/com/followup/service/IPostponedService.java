package com.followup.service;

import com.followup.entity.Postponed;

public interface IPostponedService {
    Postponed addPostponed(Postponed postponed);
    Boolean deletePostponed(Long id);
}
