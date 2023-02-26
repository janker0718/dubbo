/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.metrics.collector.sample;

import org.apache.dubbo.metrics.model.Metric;
import org.apache.dubbo.metrics.model.MetricsKey;
import org.apache.dubbo.metrics.model.sample.MetricSample;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public interface MetricsCountSampler<S, K, M extends Metric> extends MetricsSampler {

    void inc(S source, K metricName);

    void dec(S source, K metricName);

    void incOnEvent(S source, K metricName);

    void decOnEvent(S source, K metricName);

    void addRT(S source, Long rt);

    Optional<ConcurrentMap<M, AtomicLong>> getCount(K metricName);

    <R extends MetricSample> List<R> collectRT(MetricSampleFactory<M, R> factory);

    interface MetricSampleFactory<M, R extends MetricSample> {
        R newInstance(MetricsKey key, M metric, Long count);
    }
}