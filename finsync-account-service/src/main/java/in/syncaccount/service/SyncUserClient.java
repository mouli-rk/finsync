package in.syncaccount.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.syncaccount.model.CommonModel;

@FeignClient("FINSYNC-USER-SERVICE")
public interface SyncUserClient {
	
	@PostMapping("api/user/createUser")
	public CommonModel createUser(@RequestBody CommonModel userModel);

}
