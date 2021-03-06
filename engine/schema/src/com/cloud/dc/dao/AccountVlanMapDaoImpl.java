// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.dc.dao;

import java.util.List;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import com.cloud.dc.AccountVlanMapVO;
import com.cloud.utils.db.GenericDaoBase;
import com.cloud.utils.db.SearchBuilder;
import com.cloud.utils.db.SearchCriteria;

@Component
@Local(value={AccountVlanMapDao.class})
public class AccountVlanMapDaoImpl extends GenericDaoBase<AccountVlanMapVO, Long> implements AccountVlanMapDao {
    
	protected SearchBuilder<AccountVlanMapVO> AccountSearch;
	protected SearchBuilder<AccountVlanMapVO> VlanSearch;
	protected SearchBuilder<AccountVlanMapVO> AccountVlanSearch;
	
	@Override
	public List<AccountVlanMapVO> listAccountVlanMapsByAccount(long accountId) {
		SearchCriteria<AccountVlanMapVO> sc = AccountSearch.create();
    	sc.setParameters("accountId", accountId);
    	return listIncludingRemovedBy(sc);
	}
	
	@Override
	public List<AccountVlanMapVO> listAccountVlanMapsByVlan(long vlanDbId) {
		SearchCriteria<AccountVlanMapVO> sc = VlanSearch.create();
    	sc.setParameters("vlanDbId", vlanDbId);
    	return listIncludingRemovedBy(sc);
	}
	
	@Override
	public AccountVlanMapVO findAccountVlanMap(long accountId, long vlanDbId) {
		SearchCriteria<AccountVlanMapVO> sc = AccountVlanSearch.create();
		sc.setParameters("accountId", accountId);
		sc.setParameters("vlanDbId", vlanDbId);
		return findOneIncludingRemovedBy(sc);
	}
	
    public AccountVlanMapDaoImpl() {
    	AccountSearch = createSearchBuilder();
    	AccountSearch.and("accountId", AccountSearch.entity().getAccountId(), SearchCriteria.Op.EQ);
        AccountSearch.done();
        
        VlanSearch = createSearchBuilder();
    	VlanSearch.and("vlanDbId", VlanSearch.entity().getVlanDbId(), SearchCriteria.Op.EQ);
        VlanSearch.done();
        
        AccountVlanSearch = createSearchBuilder();
        AccountVlanSearch.and("accountId", AccountVlanSearch.entity().getAccountId(), SearchCriteria.Op.EQ);
        AccountVlanSearch.and("vlanDbId", AccountVlanSearch.entity().getVlanDbId(), SearchCriteria.Op.EQ);
        AccountVlanSearch.done();
    }
    
}
