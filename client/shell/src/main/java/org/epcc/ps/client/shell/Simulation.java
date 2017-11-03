/*
 * Copyright (C) 2017 the predator-prey-model authors.
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
 *
 */

package org.epcc.ps.client.shell;

import org.epcc.ps.client.shell.command.SimulationCommand;
import org.epcc.ps.client.shell.proxy.SimulationTimeMeasurementProxyHandler;

import java.lang.reflect.Proxy;

/**
 * Main entrance.
 *
 * @author shaohan.yin
 * @since 0.0.1
 * Created on 29/10/2017
 */
public class Simulation {

    public static void main(String[] args) {
        ((SimulationCommand) Proxy.newProxyInstance(SimulationCommand.class.getClassLoader(),
                new Class[]{SimulationCommand.class},
                new SimulationTimeMeasurementProxyHandler())).simulate(args);
    }
}
