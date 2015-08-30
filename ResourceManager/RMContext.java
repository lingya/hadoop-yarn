/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.server.resourcemanager;

import java.util.concurrent.ConcurrentMap;

import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.NodeId;
import org.apache.hadoop.yarn.event.Dispatcher;
import org.apache.hadoop.yarn.server.resourcemanager.recovery.RMStateStore;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.RMApp;
import org.apache.hadoop.yarn.server.resourcemanager.rmapp.attempt.AMLivelinessMonitor;
import org.apache.hadoop.yarn.server.resourcemanager.rmcontainer.ContainerAllocationExpirer;
import org.apache.hadoop.yarn.server.resourcemanager.rmnode.RMNode;
import org.apache.hadoop.yarn.server.resourcemanager.security.AMRMTokenSecretManager;
import org.apache.hadoop.yarn.server.resourcemanager.security.ClientToAMTokenSecretManagerInRM;
import org.apache.hadoop.yarn.server.resourcemanager.security.DelegationTokenRenewer;
import org.apache.hadoop.yarn.server.resourcemanager.security.RMContainerTokenSecretManager;
import org.apache.hadoop.yarn.server.resourcemanager.security.NMTokenSecretManagerInRM;
import org.apache.hadoop.yarn.server.resourcemanager.security.RMDelegationTokenSecretManager;

/**
 * Context of the ResourceManager.
 * ��Դ�����������Ľӿ��࣬������������Դ������ȡ����
 */
public interface RMContext {
  //��ȡ����ַ���������ʵ��
  Dispatcher getDispatcher();
  //��ԴӦ��״̬�洢����
  RMStateStore getStateStore();
  //��ȡӦ�ó����б�
  ConcurrentMap<ApplicationId, RMApp> getRMApps();
  //��ȡ���ƽڵ�ӳ���б�
  ConcurrentMap<String, RMNode> getInactiveRMNodes();
  //��ȡID�ڵ�ӳ���б�
  ConcurrentMap<NodeId, RMNode> getRMNodes();
  //��ȡ�����е�AM����߳�
  AMLivelinessMonitor getAMLivelinessMonitor();
  //��ȡ���н�����AM����߳�
  AMLivelinessMonitor getAMFinishingMonitor();
  //��ȡ��ʱ��ض���
  ContainerAllocationExpirer getContainerAllocationExpirer();
  //��֤���
  DelegationTokenRenewer getDelegationTokenRenewer();
  AMRMTokenSecretManager getAMRMTokenSecretManager();
  RMContainerTokenSecretManager getContainerTokenSecretManager();
  NMTokenSecretManagerInRM getNMTokenSecretManager();
  ClientToAMTokenSecretManagerInRM getClientToAMTokenSecretManager();
  
  void setClientRMService(ClientRMService clientRMService);
  //��ȡ�û��������
  ClientRMService getClientRMService();
  
  RMDelegationTokenSecretManager getRMDelegationTokenSecretManager();

  void setRMDelegationTokenSecretManager(
      RMDelegationTokenSecretManager delegationTokenSecretManager);
}