/*
 * Copyright (C) 2017-Present Pivotal Software, Inc. All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the under the Apache License,
 * Version 2.0 (the "License”); you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.pivotal.ecosystem.mssqlserver.broker;

import io.pivotal.ecosystem.mssqlserver.broker.connector.SqlServerServiceInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.GetServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.instance.*;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@TestConfiguration
class SharedConfig {

    static final String SI_ID = "deleteme";
    private static final String SB_ID = "deletemetoo";
    private static final String SD_ID = "SQLServer";
    private static final String PLAN_ID = "oneNodeCluster";

    static final String USER_ID = "aUser";

    @Bean
    @Qualifier("default")
    CreateServiceInstanceRequest createServiceInstanceDefaultRequest() {
        return CreateServiceInstanceRequest.builder()
                .serviceInstanceId(SI_ID)
                .serviceDefinitionId(SD_ID)
                .planId(PLAN_ID)
                .build();
    }

    @Bean
    @Qualifier("custom")
    CreateServiceInstanceRequest createServiceInstanceCustomRequest() {
        Map<String, Object> m = new HashMap<>();
        m.put(SqlServerServiceInfo.DATABASE, SI_ID);

        return CreateServiceInstanceRequest.builder()
                .serviceDefinitionId(SD_ID)
                .serviceInstanceId(SI_ID)
                .planId(PLAN_ID)
                .parameters(m)
                .build();
    }

    @Bean
    @Qualifier("custom")
    CreateServiceInstanceBindingRequest createServiceInstanceBindingCustomRequest() {
        Map<String, Object> m = new HashMap<>();
        m.put(SqlServerServiceInfo.USERNAME, USER_ID);

        return CreateServiceInstanceBindingRequest.builder()
                .bindingId(SB_ID)
                .serviceDefinitionId(SD_ID)
                .serviceInstanceId(SI_ID)
                .planId(PLAN_ID)
                .parameters(m)
                .build();
    }

    @Bean
    @Qualifier("default")
    CreateServiceInstanceBindingRequest createServiceInstanceBindingDefaultRequest() {
        return CreateServiceInstanceBindingRequest.builder()
                .serviceInstanceId(SI_ID)
                .bindingId(SB_ID)
                .serviceDefinitionId(SD_ID)
                .serviceInstanceId(SI_ID)
                .planId(PLAN_ID)
                .build();
    }

    @Bean
    GetServiceInstanceRequest getServiceInstanceRequest() {
        return GetServiceInstanceRequest.builder()
                .serviceInstanceId(SI_ID)
                .build();
    }

    @Bean
    GetLastServiceOperationRequest getLastServiceOperationRequest() {
        return GetLastServiceOperationRequest.builder()
                .serviceInstanceId(SI_ID)
                .build();
    }

    @Bean
    UpdateServiceInstanceRequest updateServiceInstanceRequest() {
        return UpdateServiceInstanceRequest.builder()
                .serviceInstanceId(SI_ID)
                .build();
    }

    @Bean
    GetServiceInstanceBindingRequest getServiceInstanceBindingRequest() {
        return GetServiceInstanceBindingRequest.builder()
                .serviceInstanceId(SI_ID)
                .bindingId(SB_ID)
                .build();
    }

    @Bean
    DeleteServiceInstanceBindingRequest deleteServiceInstanceBindingRequest() {
        return DeleteServiceInstanceBindingRequest.builder()
                .serviceInstanceId(SI_ID)
                .bindingId(SB_ID)
                .build();
    }

    @Bean
    DeleteServiceInstanceRequest deleteServiceInstanceRequest() {
        return DeleteServiceInstanceRequest.builder()
                .serviceInstanceId(SI_ID)
                .build();
    }
}