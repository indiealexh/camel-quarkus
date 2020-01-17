/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.core;

import java.io.File;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import org.apache.camel.Message;

@Recorder
public class CoreAttachmentsRecorder {

    public RuntimeValue<UploadAttacher> createNoOpUploadAttacher() {
        return new RuntimeValue<>((File localFile, String fileName, Message message) -> {
            throw new RuntimeException(
                    String.format(
                            "File %s will not be attached to message %s because camel-quarkus-attachments is not in the class path."
                                    + " You have several options to handle this situation:"
                                    + " (a) Add camel-quarkus-attachments dependency to your project if you want Camel to attach the uploads to Camel messages"
                                    + " (b) Disable the uploads altogether by setting quarkus.http.body.handle-file-uploads = false in your application.proprties"
                                    + " (c) Ignore this message because it is perhaps caused by clients out of your control",
                            fileName,
                            message));
        });
    }
}
