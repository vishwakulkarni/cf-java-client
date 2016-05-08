/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.reactor.client.v2.organizationquotadefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudfoundry.client.v2.organizationquotadefinitions.CreateOrganizationQuotaDefinitionRequest;
import org.cloudfoundry.client.v2.organizationquotadefinitions.CreateOrganizationQuotaDefinitionResponse;
import org.cloudfoundry.client.v2.organizationquotadefinitions.DeleteOrganizationQuotaDefinitionRequest;
import org.cloudfoundry.client.v2.organizationquotadefinitions.DeleteOrganizationQuotaDefinitionResponse;
import org.cloudfoundry.client.v2.organizationquotadefinitions.GetOrganizationQuotaDefinitionRequest;
import org.cloudfoundry.client.v2.organizationquotadefinitions.GetOrganizationQuotaDefinitionResponse;
import org.cloudfoundry.client.v2.organizationquotadefinitions.ListOrganizationQuotaDefinitionsRequest;
import org.cloudfoundry.client.v2.organizationquotadefinitions.ListOrganizationQuotaDefinitionsResponse;
import org.cloudfoundry.client.v2.organizationquotadefinitions.OrganizationQuotaDefinitions;
import org.cloudfoundry.client.v2.organizationquotadefinitions.UpdateOrganizationQuotaDefinitionRequest;
import org.cloudfoundry.client.v2.organizationquotadefinitions.UpdateOrganizationQuotaDefinitionResponse;
import org.cloudfoundry.reactor.client.v2.AbstractClientV2Operations;
import org.cloudfoundry.reactor.util.AuthorizationProvider;
import reactor.core.publisher.Mono;
import reactor.io.netty.http.HttpClient;

import static org.cloudfoundry.util.tuple.TupleUtils.function;

/**
 * The Reactor-based implementation of {@link OrganizationQuotaDefinitions}
 */
public final class ReactorOrganizationQuotaDefinitions extends AbstractClientV2Operations implements OrganizationQuotaDefinitions {

    /**
     * Creates an instance
     *
     * @param authorizationProvider the {@link AuthorizationProvider} to use when communicating with the server
     * @param httpClient            the {@link HttpClient} to use when communicating with the server
     * @param objectMapper          the {@link ObjectMapper} to use when communicating with the server
     * @param root                  the root URI of the server.  Typically something like {@code https://uaa.run.pivotal.io}.
     */
    public ReactorOrganizationQuotaDefinitions(AuthorizationProvider authorizationProvider, HttpClient httpClient, ObjectMapper objectMapper, Mono<String> root) {
        super(authorizationProvider, httpClient, objectMapper, root);
    }

    @Override
    public Mono<CreateOrganizationQuotaDefinitionResponse> create(CreateOrganizationQuotaDefinitionRequest request) {
        return post(request, CreateOrganizationQuotaDefinitionResponse.class, function((builder, validRequest) -> builder.pathSegment("v2", "quota_definitions")));
    }

    @Override
    public Mono<DeleteOrganizationQuotaDefinitionResponse> delete(DeleteOrganizationQuotaDefinitionRequest request) {
        return delete(request, DeleteOrganizationQuotaDefinitionResponse.class,
            function((builder, validRequest) -> builder.pathSegment("v2", "quota_definitions", validRequest.getOrganizationQuotaDefinitionId())));
    }

    @Override
    public Mono<GetOrganizationQuotaDefinitionResponse> get(GetOrganizationQuotaDefinitionRequest request) {
        return get(request, GetOrganizationQuotaDefinitionResponse.class,
            function((builder, validRequest) -> builder.pathSegment("v2", "quota_definitions", validRequest.getOrganizationQuotaDefinitionId())));
    }

    @Override
    public Mono<ListOrganizationQuotaDefinitionsResponse> list(ListOrganizationQuotaDefinitionsRequest request) {
        return get(request, ListOrganizationQuotaDefinitionsResponse.class, function((builder, validRequest) -> builder.pathSegment("v2", "quota_definitions")));
    }

    @Override
    public Mono<UpdateOrganizationQuotaDefinitionResponse> update(UpdateOrganizationQuotaDefinitionRequest request) {
        return put(request, UpdateOrganizationQuotaDefinitionResponse.class,
            function((builder, validRequest) -> builder.pathSegment("v2", "quota_definitions", validRequest.getOrganizationQuotaDefinitionId())));
    }

}