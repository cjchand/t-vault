// =========================================================================
// Copyright 2018 T-Mobile, US
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// See the readme.txt file for additional language around disclaimer of warranties.
// =========================================================================

package com.tmobile.cso.vault.api.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmobile.cso.vault.api.controller.ControllerUtil;
import com.tmobile.cso.vault.api.model.AWSRole;
import com.tmobile.cso.vault.api.model.Safe;
import com.tmobile.cso.vault.api.model.SafeGroup;
import com.tmobile.cso.vault.api.model.SafeUser;
import com.tmobile.cso.vault.api.process.Response;
import com.tmobile.cso.vault.api.service.SafesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(description = "Manage Safes/SDBs", position = 16)
public class SDBControllerV2 {

	@Value("${vault.auth.method}")
	private String vaultAuthMethod;

	@Autowired
	private SafesService safesService;
	/**
	 * Gets all SDB folders
	 * @param token
	 * @param path
	 * @return
	 */
	@ApiOperation(value = "${SafesController.getFolders.value}", notes = "${SafesController.getFolders.notes}")
	@GetMapping(value="/v2/sdb/folder",produces="application/json")
	public ResponseEntity<String> getFolders(@RequestHeader(value="vault-token") String token, @RequestParam("path") String path){
		return safesService.getFolders(token, path);
		
	}
	/**
	 * Gets information about SDB
	 * @param token
	 * @param path
	 * @return
	 */
	@ApiOperation(value = "${SafesController.getInfo.value}", notes = "${SafesController.getInfo.notes}")
	@GetMapping(value="/v2/sdb/folder/{path}",produces="application/json")
	public ResponseEntity<String> getInfo(@RequestHeader(value="vault-token") String token, @RequestParam("path") String path){
		return safesService.getInfo(token, path);
	}
	/**
	 * Creates a SDB folder
	 * @param token
	 * @param path
	 * @return
	 */
	@PostMapping(value="/v2/sdb/folder",produces="application/json")
	public ResponseEntity<String> createfolder(@RequestHeader(value="vault-token") String token, @RequestParam("path") String path){
		return safesService.createfolder(token, path);		
	}
	/**
	 * Updates a Safe
	 * @param token
	 * @param safe
	 * @return
	 */
	@ApiOperation(value = "${SafesController.updateSafe.value}", notes = "${SafesController.updateSafe.notes}")
	@PutMapping(value="/v2/sdb",consumes="application/json",produces="application/json")
	public ResponseEntity<String> updateSafe(@RequestHeader(value="vault-token" ) String token, @RequestBody Safe safe){
		return safesService.updateSafe(token, safe);
	}
	/**
	 * Creates a Safe
	 * @param token
	 * @param safe
	 * @return
	 */
	@ApiOperation(value = "${SafesController.createSafe.value}", notes = "${SafesController.createSafe.notes}")
	@PostMapping(value="/v2/sdb", consumes="application/json",produces="application/json")
	public ResponseEntity<String> createSafe(@RequestHeader(value="vault-token" ) String token, @RequestBody Safe safe){
		return safesService.createSafe(token, safe);
	}
	/**
	 * Deletes a Safe
	 * @param token
	 * @param safe
	 * @return
	 */
	@ApiOperation(value = "${SafesController.deleteSafe.value}", notes = "${SafesController.deleteSafe.notes}")
	@DeleteMapping(value="/v2/sdb",produces="application/json")
	public ResponseEntity<String> deleteSafe(@RequestHeader(value="vault-token") String token, @RequestBody Safe safe){
		return safesService.deleteSafe(token, safe);
	}
	/**
	 * Gets Safe 
	 * @param token
	 * @param path
	 * @return
	 */
	@ApiOperation(value = "${SafesController.getSafe.value}", notes = "${SafesController.getSafe.notes}")
	@GetMapping(value="/v2/sdb",produces="application/json")
	public ResponseEntity<String> getSafe(@RequestHeader(value="vault-token") String token, @RequestParam("path") String path){
		return safesService.getSafe(token, path);
	}
	/**
	 * Adds user with a Safe
	 * @param token
	 * @param safeUser
	 * @return
	 */
	@ApiOperation(value = "${SafesController.addUserToSafe.value}", notes = "${SafesController.addUserToSafe.notes}")
	@PostMapping(value="/v2/sdb/user",consumes="application/json",produces="application/json")
	public ResponseEntity<String> addUsertoSafe(@RequestHeader(value="vault-token") String token, @RequestBody SafeUser safeUser){
		return safesService.addUserToSafe(token, safeUser);
	}
	/**
	 * Removes user from Safe
	 * @param token
	 * @param safeUser
	 * @return
	 */
	@ApiOperation(value = "${SafesController.removeUserFromSafe.value}", notes = "${SafesController.removeUserFromSafe.notes}")
	@DeleteMapping(value="/v2/sdb/user",consumes="application/json",produces="application/json")
	public ResponseEntity<String> deleteUserSafe(@RequestHeader(value="vault-token") String token, @RequestBody SafeUser safeUser){
		return safesService.removeUserFromSafe(token, safeUser);
	}
	/**
	 * Adds a usersgroup with a safe
	 * @param token
	 * @param safeGroup
	 * @return
	 */
	@ApiOperation(value = "${SafesController.deleteGroupFromSafe.value}", notes = "${SafesController.deleteGroupFromSafe.notes}")
	@DeleteMapping (value="/v2/sdb/group",consumes="application/json",produces="application/json")
	public ResponseEntity<String> deleteGroupFromSafe(@RequestHeader(value="vault-token") String token, @RequestBody SafeGroup safeGroup){
		return safesService.removeGroupFromSafe(token, safeGroup);
	}
	/**
	 * Adds AWS role to a Safe 
	 * @param token
	 * @param awsRole
	 * @return
	 */
	@ApiOperation(value = "${SafesController.addAWSRoleToSafe.value}", notes = "${SafesController.addAWSRoleToSafe.notes}")
	@PostMapping (value="/v2/sdb/role",consumes="application/json",produces="application/json")
	public ResponseEntity<String> addAwsRoleToSafe(@RequestHeader(value="vault-token") String token, @RequestBody AWSRole awsRole){
		return safesService.addAwsRoleToSafe(token, awsRole);
	}

}
