/*
 * Copyright 2019 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.netflix.spinnaker.halyard.cli.command.v1.config.providers.alicloud;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.netflix.spinnaker.halyard.cli.command.v1.config.providers.bakery.AbstractEditBaseImageCommand;
import com.netflix.spinnaker.halyard.config.model.v1.node.BaseImage;
import com.netflix.spinnaker.halyard.config.model.v1.providers.alicloud.AliCloudBaseImage;

@Parameters(separators = "=")
public class AliCloudEditBaseImageCommand extends AbstractEditBaseImageCommand<AliCloudBaseImage> {
  @Override
  protected String getProviderName() {
    return "alicloud";
  }

  @Parameter(names = "--region", description = AliCloudCommandProperties.REGIONS_DESCRIPTION)
  private String region;

  @Parameter(
      names = "--instance-type",
      description = AliCloudCommandProperties.INSTANCE_TYPE_DESCRIPTION)
  private String instanceType;

  @Parameter(
      names = "--source-image",
      description = AliCloudCommandProperties.SOURCE_IMAGE_DESCRIPTION)
  private String sourceImage;

  @Parameter(
      names = "--ssh-user-name",
      description = AliCloudCommandProperties.SSH_USER_NAME_DESCRIPTION)
  private String sshUserName;

  @Override
  protected BaseImage editBaseImage(AliCloudBaseImage baseImage) {
    AliCloudBaseImage.AliCloudImageSettings imageSettings = baseImage.getBaseImage();
    imageSettings =
        imageSettings != null ? imageSettings : new AliCloudBaseImage.AliCloudImageSettings();
    baseImage.setBaseImage(imageSettings);

    AliCloudBaseImage.AliCloudVirtualizationSettings virtualizationSettings =
        baseImage.getVirtualizationSettings();
    virtualizationSettings =
        virtualizationSettings != null
            ? virtualizationSettings
            : new AliCloudBaseImage.AliCloudVirtualizationSettings();
    virtualizationSettings.setRegion(isSet(region) ? region : virtualizationSettings.getRegion());
    virtualizationSettings.setInstanceType(
        isSet(instanceType) ? instanceType : virtualizationSettings.getInstanceType());
    virtualizationSettings.setSourceImage(
        isSet(sourceImage) ? sourceImage : virtualizationSettings.getSourceImage());
    virtualizationSettings.setSshUserName(
        isSet(sshUserName) ? sshUserName : virtualizationSettings.getSshUserName());
    baseImage.setVirtualizationSettings(virtualizationSettings);

    return baseImage;
  }
}
