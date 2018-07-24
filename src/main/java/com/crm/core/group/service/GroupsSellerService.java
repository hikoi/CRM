package com.crm.core.group.service;

import java.util.List;

public interface GroupsSellerService{

    void save(String groupsId, String sellerId);

    void saveList(String groupsId, List<String> sellerIds);
}
