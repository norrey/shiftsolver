package io.carer.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.apache.log4j.BasicConfigurator;


/**
 * Solve the problem
 *
 */
public class ScheduleSolver {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static String THE_PROBLEM = "{\n" +
"  \"shifts\": [\n" +
"    {\n" +
"      \"id\": 2,\n" +
"      \"shiftId\": 2,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 35,\n" +
"        \"clientId\": 35,\n" +
"        \"name\": \"Felo Cheru\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 2,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": false,\n" +
"          \"requiresSupportOwnCar\": false,\n" +
"          \"requiresCriminalRecordsCheck\": true,\n" +
"          \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            {\n" +
"              \"id\": 2,\n" +
"              \"skillLevelId\": 2\n" +
"            }\n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 38,\n" +
"          \"workerId\": 38,\n" +
"          \"name\": \"Mary Ann\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": true,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": false,\n" +
"          \"religiousObservationSupport\": true,\n" +
"          \"hasDrivingLicense\": true\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-17T15:14:53Z\",\n" +
"        \"end\": \"2016-12-17T18:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 4,\n" +
"      \"shiftId\": 4,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 9,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 39,\n" +
"        \"clientId\": 39,\n" +
"        \"name\": \"Mosco Murey\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 5,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": false,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 43,\n" +
"          \"workerId\": 43,\n" +
"          \"name\": \"James Bond\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": true,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": true,\n" +
"          \"religiousObservationSupport\": true,\n" +
"          \"hasDrivingLicense\": false\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-19T15:16:49Z\",\n" +
"        \"end\": \"2016-12-19T00:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 5,\n" +
"      \"shiftId\": 5,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 46,\n" +
"        \"clientId\": 46,\n" +
"        \"name\": \"Wesley Kante\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 7,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": false,\n" +
"          \"requiresSupportOwnCar\": false,\n" +
"          \"requiresCriminalRecordsCheck\": true,\n" +
"          \"shiftType\": \"REGULAR_FLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-22T06:00:00Z\",\n" +
"        \"end\": \"2016-12-22T06:51:41Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 6,\n" +
"      \"shiftId\": 6,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 12,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 37,\n" +
"        \"clientId\": 37,\n" +
"        \"name\": \"Leo Mambo\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 3,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-19T06:00:00Z\",\n" +
"        \"end\": \"2016-12-19T18:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 7,\n" +
"      \"shiftId\": 7,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 12,\n" +
"      \"maxWeekHours\": 35,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 48,\n" +
"        \"clientId\": 48,\n" +
"        \"name\": \"Hazard Chelsea\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 8,\n" +
"          \"requiresNightWork\": false,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 38,\n" +
"          \"workerId\": 38,\n" +
"          \"name\": \"Mary Ann\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": true,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": false,\n" +
"          \"religiousObservationSupport\": true,\n" +
"          \"hasDrivingLicense\": true\n" +
"        },\n" +
"        {\n" +
"          \"id\": 41,\n" +
"          \"workerId\": 41,\n" +
"          \"name\": \"Jacky Warren\",\n" +
"          \"nightSupport\": false,\n" +
"          \"hasCar\": true,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": false,\n" +
"          \"religiousObservationSupport\": true,\n" +
"          \"hasDrivingLicense\": true\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-23T12:00:00Z\",\n" +
"        \"end\": \"2016-12-24T12:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 8,\n" +
"      \"shiftId\": 8,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 36,\n" +
"        \"clientId\": 36,\n" +
"        \"name\": \"Luis Adrian\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 4,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-20T06:56:59Z\",\n" +
"        \"end\": \"2016-12-20T12:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 9,\n" +
"      \"shiftId\": 9,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 34,\n" +
"        \"clientId\": 34,\n" +
"        \"name\": \"John Doe\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 1,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": true,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            {\n" +
"              \"id\": 1,\n" +
"              \"skillLevelId\": 1\n" +
"            },\n" +
"            {\n" +
"              \"id\": 3,\n" +
"              \"skillLevelId\": 3\n" +
"            }\n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            {\n" +
"              \"id\": 1,\n" +
"              \"activityId\": 1\n" +
"            }\n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-21T06:00:00Z\",\n" +
"        \"end\": \"2016-12-21T18:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 10,\n" +
"      \"shiftId\": 10,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 10,\n" +
"      \"maxWeekHours\": 38,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 39,\n" +
"        \"clientId\": 39,\n" +
"        \"name\": \"Mosco Murey\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 5,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": false,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-22T00:00:00Z\",\n" +
"        \"end\": \"2016-12-23T12:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 11,\n" +
"      \"shiftId\": 11,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 12,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 35,\n" +
"        \"clientId\": 35,\n" +
"        \"name\": \"Felo Cheru\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 2,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": false,\n" +
"          \"requiresSupportOwnCar\": false,\n" +
"          \"requiresCriminalRecordsCheck\": true,\n" +
"          \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            {\n" +
"              \"id\": 2,\n" +
"              \"skillLevelId\": 2\n" +
"            }\n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 45,\n" +
"          \"workerId\": 45,\n" +
"          \"name\": \"Jose Howard\",\n" +
"          \"nightSupport\": null,\n" +
"          \"hasCar\": null,\n" +
"          \"criminalRecordsChecked\": null,\n" +
"          \"weekendSupport\": null,\n" +
"          \"religiousObservationSupport\": null,\n" +
"          \"hasDrivingLicense\": null\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-20T07:00:53Z\",\n" +
"        \"end\": \"2016-12-20T18:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 12,\n" +
"      \"shiftId\": 12,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 12,\n" +
"      \"maxWeekHours\": 30,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 42,\n" +
"        \"clientId\": 42,\n" +
"        \"name\": \"Norre Osako\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 6,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": true,\n" +
"          \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            {\n" +
"              \"id\": 4,\n" +
"              \"skillLevelId\": 4\n" +
"            }\n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-22T06:00:00Z\",\n" +
"        \"end\": \"2016-12-22T18:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 13,\n" +
"      \"shiftId\": 13,\n" +
"      \"handOverTime\": 35,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 37,\n" +
"        \"clientId\": 37,\n" +
"        \"name\": \"Leo Mambo\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 3,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 43,\n" +
"          \"workerId\": 43,\n" +
"          \"name\": \"James Bond\",\n" +
"          \"nightSupport\": false,\n" +
"          \"hasCar\": false,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": true,\n" +
"          \"religiousObservationSupport\": false,\n" +
"          \"hasDrivingLicense\": false\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-24T06:00:00Z\",\n" +
"        \"end\": \"2016-12-24T12:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 14,\n" +
"      \"shiftId\": 14,\n" +
"      \"handOverTime\": 40,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 36,\n" +
"        \"clientId\": 36,\n" +
"        \"name\": \"Luis Adrian\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 4,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        \n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-21T00:00:00Z\",\n" +
"        \"end\": \"2016-12-22T06:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"shiftId\": 1,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 34,\n" +
"        \"clientId\": 34,\n" +
"        \"name\": \"John Doe\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 1,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": true,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            {\n" +
"              \"id\": 1,\n" +
"              \"skillLevelId\": 1\n" +
"            },\n" +
"            {\n" +
"              \"id\": 3,\n" +
"              \"skillLevelId\": 3\n" +
"            }\n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            {\n" +
"              \"id\": 1,\n" +
"              \"activityId\": 1\n" +
"            }\n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 38,\n" +
"          \"workerId\": 38,\n" +
"          \"name\": \"Mary Ann\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": true,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": false,\n" +
"          \"religiousObservationSupport\": true,\n" +
"          \"hasDrivingLicense\": true\n" +
"        },\n" +
"        {\n" +
"          \"id\": 40,\n" +
"          \"workerId\": 40,\n" +
"          \"name\": \"Lucy Pat\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": false,\n" +
"          \"criminalRecordsChecked\": false,\n" +
"          \"weekendSupport\": true,\n" +
"          \"religiousObservationSupport\": false,\n" +
"          \"hasDrivingLicense\": true\n" +
"        },\n" +
"        {\n" +
"          \"id\": 40,\n" +
"          \"workerId\": 40,\n" +
"          \"name\": \"Lucy Pat\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": false,\n" +
"          \"criminalRecordsChecked\": false,\n" +
"          \"weekendSupport\": true,\n" +
"          \"religiousObservationSupport\": false,\n" +
"          \"hasDrivingLicense\": true\n" +
"        },\n" +
"        {\n" +
"          \"id\": 45,\n" +
"          \"workerId\": 45,\n" +
"          \"name\": \"Jose Howard\",\n" +
"          \"nightSupport\": null,\n" +
"          \"hasCar\": null,\n" +
"          \"criminalRecordsChecked\": null,\n" +
"          \"weekendSupport\": null,\n" +
"          \"religiousObservationSupport\": null,\n" +
"          \"hasDrivingLicense\": null\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-17T15:13:52Z\",\n" +
"        \"end\": \"2016-12-17T00:00:00Z\"\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 3,\n" +
"      \"shiftId\": 3,\n" +
"      \"handOverTime\": 30,\n" +
"      \"minDayHours\": 4,\n" +
"      \"maxDayHours\": 8,\n" +
"      \"maxWeekHours\": 40,\n" +
"      \"status\": \"PENDING\",\n" +
"      \"client\": {\n" +
"        \"id\": 36,\n" +
"        \"clientId\": 36,\n" +
"        \"name\": \"Luis Adrian\",\n" +
"        \"shiftRequirements\": {\n" +
"          \"id\": 4,\n" +
"          \"requiresNightWork\": true,\n" +
"          \"requiresReligiousObservation\": true,\n" +
"          \"requiresSupportOwnCar\": true,\n" +
"          \"requiresCriminalRecordsCheck\": false,\n" +
"          \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"          \"requiredSupportSkillLevels\": [\n" +
"            \n" +
"          ],\n" +
"          \"requiredSupportActivities\": [\n" +
"            \n" +
"          ],\n" +
"          \"compatibleSupport\": [\n" +
"            \n" +
"          ]\n" +
"        }\n" +
"      },\n" +
"      \"workers\": [\n" +
"        {\n" +
"          \"id\": 44,\n" +
"          \"workerId\": 44,\n" +
"          \"name\": \"Kelvin Mata\",\n" +
"          \"nightSupport\": true,\n" +
"          \"hasCar\": false,\n" +
"          \"criminalRecordsChecked\": true,\n" +
"          \"weekendSupport\": false,\n" +
"          \"religiousObservationSupport\": true,\n" +
"          \"hasDrivingLicense\": false\n" +
"        }\n" +
"      ],\n" +
"      \"shiftDate\": {\n" +
"        \"start\": \"2016-12-18T15:15:48Z\",\n" +
"        \"end\": \"2016-12-19T12:00:00Z\"\n" +
"      }\n" +
"    }\n" +
"  ],\n" +
"  \"supportSkillLevels\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"skillLevelId\": 1\n" +
"    },\n" +
"    {\n" +
"      \"id\": 2,\n" +
"      \"skillLevelId\": 2\n" +
"    }\n" +
"  ],\n" +
"  \"supportActivities\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"activityId\": 1\n" +
"    }\n" +
"  ],\n" +
"  \"shiftDates\": [\n" +
"    {\n" +
"      \"start\": \"2016-12-17T15:14:53Z\",\n" +
"      \"end\": \"2016-12-17T18:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-19T15:16:49Z\",\n" +
"      \"end\": \"2016-12-19T00:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-22T06:00:00Z\",\n" +
"      \"end\": \"2016-12-22T06:51:41Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-19T06:00:00Z\",\n" +
"      \"end\": \"2016-12-19T18:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-23T12:00:00Z\",\n" +
"      \"end\": \"2016-12-24T12:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-20T06:56:59Z\",\n" +
"      \"end\": \"2016-12-20T12:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-21T06:00:00Z\",\n" +
"      \"end\": \"2016-12-21T18:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-22T00:00:00Z\",\n" +
"      \"end\": \"2016-12-23T12:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-20T07:00:53Z\",\n" +
"      \"end\": \"2016-12-20T18:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-22T06:00:00Z\",\n" +
"      \"end\": \"2016-12-22T18:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-24T06:00:00Z\",\n" +
"      \"end\": \"2016-12-24T12:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-21T00:00:00Z\",\n" +
"      \"end\": \"2016-12-22T06:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-17T15:13:52Z\",\n" +
"      \"end\": \"2016-12-17T00:00:00Z\"\n" +
"    },\n" +
"    {\n" +
"      \"start\": \"2016-12-18T15:15:48Z\",\n" +
"      \"end\": \"2016-12-19T12:00:00Z\"\n" +
"    }\n" +
"  ],\n" +
"  \"workers\": [\n" +
"    {\n" +
"      \"id\": 38,\n" +
"      \"workerId\": 38,\n" +
"      \"name\": \"Mary Ann\",\n" +
"      \"nightSupport\": true,\n" +
"      \"hasCar\": true,\n" +
"      \"criminalRecordsChecked\": true,\n" +
"      \"weekendSupport\": false,\n" +
"      \"religiousObservationSupport\": true,\n" +
"      \"hasDrivingLicense\": true\n" +
"    },\n" +
"    {\n" +
"      \"id\": 41,\n" +
"      \"workerId\": 41,\n" +
"      \"name\": \"Jacky Warren\",\n" +
"      \"nightSupport\": false,\n" +
"      \"hasCar\": true,\n" +
"      \"criminalRecordsChecked\": true,\n" +
"      \"weekendSupport\": false,\n" +
"      \"religiousObservationSupport\": true,\n" +
"      \"hasDrivingLicense\": true\n" +
"    },\n" +
"    {\n" +
"      \"id\": 40,\n" +
"      \"workerId\": 40,\n" +
"      \"name\": \"Lucy Pat\",\n" +
"      \"nightSupport\": true,\n" +
"      \"hasCar\": false,\n" +
"      \"criminalRecordsChecked\": false,\n" +
"      \"weekendSupport\": true,\n" +
"      \"religiousObservationSupport\": false,\n" +
"      \"hasDrivingLicense\": true\n" +
"    },\n" +
"    {\n" +
"      \"id\": 43,\n" +
"      \"workerId\": 43,\n" +
"      \"name\": \"James Bond\",\n" +
"      \"nightSupport\": false,\n" +
"      \"hasCar\": false,\n" +
"      \"criminalRecordsChecked\": true,\n" +
"      \"weekendSupport\": true,\n" +
"      \"religiousObservationSupport\": false,\n" +
"      \"hasDrivingLicense\": false\n" +
"    },\n" +
"    {\n" +
"      \"id\": 44,\n" +
"      \"workerId\": 44,\n" +
"      \"name\": \"Kelvin Mata\",\n" +
"      \"nightSupport\": true,\n" +
"      \"hasCar\": false,\n" +
"      \"criminalRecordsChecked\": true,\n" +
"      \"weekendSupport\": false,\n" +
"      \"religiousObservationSupport\": true,\n" +
"      \"hasDrivingLicense\": false\n" +
"    },\n" +
"    {\n" +
"      \"id\": 45,\n" +
"      \"workerId\": 45,\n" +
"      \"name\": \"Jose Howard\",\n" +
"      \"nightSupport\": null,\n" +
"      \"hasCar\": null,\n" +
"      \"criminalRecordsChecked\": null,\n" +
"      \"weekendSupport\": null,\n" +
"      \"religiousObservationSupport\": null,\n" +
"      \"hasDrivingLicense\": null\n" +
"    },\n" +
"    {\n" +
"      \"id\": 47,\n" +
"      \"workerId\": 47,\n" +
"      \"name\": \"Tabitha Jones\",\n" +
"      \"nightSupport\": null,\n" +
"      \"hasCar\": null,\n" +
"      \"criminalRecordsChecked\": null,\n" +
"      \"weekendSupport\": null,\n" +
"      \"religiousObservationSupport\": null,\n" +
"      \"hasDrivingLicense\": null\n" +
"    }\n" +
"  ],\n" +
"  \"clients\": [\n" +
"    {\n" +
"      \"id\": 34,\n" +
"      \"clientId\": 34,\n" +
"      \"name\": \"John Doe\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 1,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": true,\n" +
"        \"requiresCriminalRecordsCheck\": true,\n" +
"        \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          {\n" +
"            \"id\": 1,\n" +
"            \"skillLevelId\": 1\n" +
"          },\n" +
"          {\n" +
"            \"id\": 3,\n" +
"            \"skillLevelId\": 3\n" +
"          }\n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          {\n" +
"            \"id\": 1,\n" +
"            \"activityId\": 1\n" +
"          }\n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 37,\n" +
"      \"clientId\": 37,\n" +
"      \"name\": \"Leo Mambo\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 3,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": true,\n" +
"        \"requiresCriminalRecordsCheck\": false,\n" +
"        \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 36,\n" +
"      \"clientId\": 36,\n" +
"      \"name\": \"Luis Adrian\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 4,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": true,\n" +
"        \"requiresCriminalRecordsCheck\": false,\n" +
"        \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 39,\n" +
"      \"clientId\": 39,\n" +
"      \"name\": \"Mosco Murey\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 5,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": false,\n" +
"        \"requiresCriminalRecordsCheck\": false,\n" +
"        \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 42,\n" +
"      \"clientId\": 42,\n" +
"      \"name\": \"Norre Osako\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 6,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": true,\n" +
"        \"requiresCriminalRecordsCheck\": true,\n" +
"        \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          {\n" +
"            \"id\": 4,\n" +
"            \"skillLevelId\": 4\n" +
"          }\n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 46,\n" +
"      \"clientId\": 46,\n" +
"      \"name\": \"Wesley Kante\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 7,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": false,\n" +
"        \"requiresSupportOwnCar\": false,\n" +
"        \"requiresCriminalRecordsCheck\": true,\n" +
"        \"shiftType\": \"REGULAR_FLEXIBLE\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 48,\n" +
"      \"clientId\": 48,\n" +
"      \"name\": \"Hazard Chelsea\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 8,\n" +
"        \"requiresNightWork\": false,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": true,\n" +
"        \"requiresCriminalRecordsCheck\": false,\n" +
"        \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 49,\n" +
"      \"clientId\": 49,\n" +
"      \"name\": \"Sarah Chepkoech\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 9,\n" +
"        \"requiresNightWork\": false,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": false,\n" +
"        \"requiresCriminalRecordsCheck\": true,\n" +
"        \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 50,\n" +
"      \"clientId\": 50,\n" +
"      \"name\": \"Tom Jerry\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 10,\n" +
"        \"requiresNightWork\": false,\n" +
"        \"requiresReligiousObservation\": true,\n" +
"        \"requiresSupportOwnCar\": true,\n" +
"        \"requiresCriminalRecordsCheck\": true,\n" +
"        \"shiftType\": \"OTHER_FLEXIBLE\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          \n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"id\": 35,\n" +
"      \"clientId\": 35,\n" +
"      \"name\": \"Felo Cheru\",\n" +
"      \"shiftRequirements\": {\n" +
"        \"id\": 2,\n" +
"        \"requiresNightWork\": true,\n" +
"        \"requiresReligiousObservation\": false,\n" +
"        \"requiresSupportOwnCar\": false,\n" +
"        \"requiresCriminalRecordsCheck\": true,\n" +
"        \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"        \"requiredSupportSkillLevels\": [\n" +
"          {\n" +
"            \"id\": 2,\n" +
"            \"skillLevelId\": 2\n" +
"          }\n" +
"        ],\n" +
"        \"requiredSupportActivities\": [\n" +
"          \n" +
"        ],\n" +
"        \"compatibleSupport\": [\n" +
"          \n" +
"        ]\n" +
"      }\n" +
"    }\n" +
"  ],\n" +
"  \"shiftRequirements\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": true,\n" +
"      \"requiresCriminalRecordsCheck\": true,\n" +
"      \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        {\n" +
"          \"id\": 1,\n" +
"          \"skillLevelId\": 1\n" +
"        },\n" +
"        {\n" +
"          \"id\": 3,\n" +
"          \"skillLevelId\": 3\n" +
"        }\n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        {\n" +
"          \"id\": 1,\n" +
"          \"activityId\": 1\n" +
"        }\n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 2,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": false,\n" +
"      \"requiresSupportOwnCar\": false,\n" +
"      \"requiresCriminalRecordsCheck\": true,\n" +
"      \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        {\n" +
"          \"id\": 2,\n" +
"          \"skillLevelId\": 2\n" +
"        }\n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 3,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": true,\n" +
"      \"requiresCriminalRecordsCheck\": false,\n" +
"      \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 4,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": true,\n" +
"      \"requiresCriminalRecordsCheck\": false,\n" +
"      \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 5,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": false,\n" +
"      \"requiresCriminalRecordsCheck\": false,\n" +
"      \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 6,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": true,\n" +
"      \"requiresCriminalRecordsCheck\": true,\n" +
"      \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        {\n" +
"          \"id\": 4,\n" +
"          \"skillLevelId\": 4\n" +
"        }\n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 7,\n" +
"      \"requiresNightWork\": true,\n" +
"      \"requiresReligiousObservation\": false,\n" +
"      \"requiresSupportOwnCar\": false,\n" +
"      \"requiresCriminalRecordsCheck\": true,\n" +
"      \"shiftType\": \"REGULAR_FLEXIBLE\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 8,\n" +
"      \"requiresNightWork\": false,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": true,\n" +
"      \"requiresCriminalRecordsCheck\": false,\n" +
"      \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 9,\n" +
"      \"requiresNightWork\": false,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": false,\n" +
"      \"requiresCriminalRecordsCheck\": true,\n" +
"      \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"id\": 10,\n" +
"      \"requiresNightWork\": false,\n" +
"      \"requiresReligiousObservation\": true,\n" +
"      \"requiresSupportOwnCar\": true,\n" +
"      \"requiresCriminalRecordsCheck\": true,\n" +
"      \"shiftType\": \"OTHER_FLEXIBLE\",\n" +
"      \"requiredSupportSkillLevels\": [\n" +
"        \n" +
"      ],\n" +
"      \"requiredSupportActivities\": [\n" +
"        \n" +
"      ],\n" +
"      \"compatibleSupport\": [\n" +
"        \n" +
"      ]\n" +
"    }\n" +
"  ],\n" +
"  \"shiftAssignments\": [\n" +
"    {\n" +
"      \"id\": 1,\n" +
"      \"worker\": {\n" +
"        \"id\": 38,\n" +
"        \"workerId\": 38,\n" +
"        \"name\": \"Mary Ann\",\n" +
"        \"nightSupport\": true,\n" +
"        \"hasCar\": true,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": false,\n" +
"        \"religiousObservationSupport\": true,\n" +
"        \"hasDrivingLicense\": true\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 1,\n" +
"        \"shiftId\": 1,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 34,\n" +
"          \"clientId\": 34,\n" +
"          \"name\": \"John Doe\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 1,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": true,\n" +
"            \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"skillLevelId\": 1\n" +
"              },\n" +
"              {\n" +
"                \"id\": 3,\n" +
"                \"skillLevelId\": 3\n" +
"              }\n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"activityId\": 1\n" +
"              }\n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 45,\n" +
"            \"workerId\": 45,\n" +
"            \"name\": \"Jose Howard\",\n" +
"            \"nightSupport\": null,\n" +
"            \"hasCar\": null,\n" +
"            \"criminalRecordsChecked\": null,\n" +
"            \"weekendSupport\": null,\n" +
"            \"religiousObservationSupport\": null,\n" +
"            \"hasDrivingLicense\": null\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-17T15:13:52Z\",\n" +
"          \"end\": \"2016-12-17T00:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 2,\n" +
"      \"worker\": {\n" +
"        \"id\": 40,\n" +
"        \"workerId\": 40,\n" +
"        \"name\": \"Lucy Pat\",\n" +
"        \"nightSupport\": true,\n" +
"        \"hasCar\": false,\n" +
"        \"criminalRecordsChecked\": false,\n" +
"        \"weekendSupport\": true,\n" +
"        \"religiousObservationSupport\": false,\n" +
"        \"hasDrivingLicense\": true\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 1,\n" +
"        \"shiftId\": 1,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 34,\n" +
"          \"clientId\": 34,\n" +
"          \"name\": \"John Doe\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 1,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": true,\n" +
"            \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"skillLevelId\": 1\n" +
"              },\n" +
"              {\n" +
"                \"id\": 3,\n" +
"                \"skillLevelId\": 3\n" +
"              }\n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"activityId\": 1\n" +
"              }\n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 45,\n" +
"            \"workerId\": 45,\n" +
"            \"name\": \"Jose Howard\",\n" +
"            \"nightSupport\": null,\n" +
"            \"hasCar\": null,\n" +
"            \"criminalRecordsChecked\": null,\n" +
"            \"weekendSupport\": null,\n" +
"            \"religiousObservationSupport\": null,\n" +
"            \"hasDrivingLicense\": null\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-17T15:13:52Z\",\n" +
"          \"end\": \"2016-12-17T00:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 3,\n" +
"      \"worker\": {\n" +
"        \"id\": 38,\n" +
"        \"workerId\": 38,\n" +
"        \"name\": \"Mary Ann\",\n" +
"        \"nightSupport\": true,\n" +
"        \"hasCar\": true,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": false,\n" +
"        \"religiousObservationSupport\": true,\n" +
"        \"hasDrivingLicense\": true\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 2,\n" +
"        \"shiftId\": 2,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 35,\n" +
"          \"clientId\": 35,\n" +
"          \"name\": \"Felo Cheru\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 2,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": false,\n" +
"            \"requiresSupportOwnCar\": false,\n" +
"            \"requiresCriminalRecordsCheck\": true,\n" +
"            \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              {\n" +
"                \"id\": 2,\n" +
"                \"skillLevelId\": 2\n" +
"              }\n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-17T15:14:53Z\",\n" +
"          \"end\": \"2016-12-17T18:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 4,\n" +
"      \"worker\": {\n" +
"        \"id\": 44,\n" +
"        \"workerId\": 44,\n" +
"        \"name\": \"Kelvin Mata\",\n" +
"        \"nightSupport\": true,\n" +
"        \"hasCar\": false,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": false,\n" +
"        \"religiousObservationSupport\": true,\n" +
"        \"hasDrivingLicense\": false\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 3,\n" +
"        \"shiftId\": 3,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 36,\n" +
"          \"clientId\": 36,\n" +
"          \"name\": \"Luis Adrian\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 4,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": false,\n" +
"            \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              \n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 44,\n" +
"            \"workerId\": 44,\n" +
"            \"name\": \"Kelvin Mata\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": false\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-18T15:15:48Z\",\n" +
"          \"end\": \"2016-12-19T12:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 5,\n" +
"      \"worker\": {\n" +
"        \"id\": 43,\n" +
"        \"workerId\": 43,\n" +
"        \"name\": \"James Bond\",\n" +
"        \"nightSupport\": false,\n" +
"        \"hasCar\": false,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": true,\n" +
"        \"religiousObservationSupport\": false,\n" +
"        \"hasDrivingLicense\": false\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 4,\n" +
"        \"shiftId\": 4,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 9,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 39,\n" +
"          \"clientId\": 39,\n" +
"          \"name\": \"Mosco Murey\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 5,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": false,\n" +
"            \"requiresCriminalRecordsCheck\": false,\n" +
"            \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              \n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 43,\n" +
"            \"workerId\": 43,\n" +
"            \"name\": \"James Bond\",\n" +
"            \"nightSupport\": false,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": false\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-19T15:16:49Z\",\n" +
"          \"end\": \"2016-12-19T00:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 6,\n" +
"      \"worker\": {\n" +
"        \"id\": 40,\n" +
"        \"workerId\": 40,\n" +
"        \"name\": \"Lucy Pat\",\n" +
"        \"nightSupport\": true,\n" +
"        \"hasCar\": false,\n" +
"        \"criminalRecordsChecked\": false,\n" +
"        \"weekendSupport\": true,\n" +
"        \"religiousObservationSupport\": false,\n" +
"        \"hasDrivingLicense\": true\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 1,\n" +
"        \"shiftId\": 1,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 34,\n" +
"          \"clientId\": 34,\n" +
"          \"name\": \"John Doe\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 1,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": true,\n" +
"            \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"skillLevelId\": 1\n" +
"              },\n" +
"              {\n" +
"                \"id\": 3,\n" +
"                \"skillLevelId\": 3\n" +
"              }\n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"activityId\": 1\n" +
"              }\n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 45,\n" +
"            \"workerId\": 45,\n" +
"            \"name\": \"Jose Howard\",\n" +
"            \"nightSupport\": null,\n" +
"            \"hasCar\": null,\n" +
"            \"criminalRecordsChecked\": null,\n" +
"            \"weekendSupport\": null,\n" +
"            \"religiousObservationSupport\": null,\n" +
"            \"hasDrivingLicense\": null\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-17T15:13:52Z\",\n" +
"          \"end\": \"2016-12-17T00:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 7,\n" +
"      \"worker\": {\n" +
"        \"id\": 38,\n" +
"        \"workerId\": 38,\n" +
"        \"name\": \"Mary Ann\",\n" +
"        \"nightSupport\": true,\n" +
"        \"hasCar\": true,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": false,\n" +
"        \"religiousObservationSupport\": true,\n" +
"        \"hasDrivingLicense\": true\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 7,\n" +
"        \"shiftId\": 7,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 12,\n" +
"        \"maxWeekHours\": 35,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 48,\n" +
"          \"clientId\": 48,\n" +
"          \"name\": \"Hazard Chelsea\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 8,\n" +
"            \"requiresNightWork\": false,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": false,\n" +
"            \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              \n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 41,\n" +
"            \"workerId\": 41,\n" +
"            \"name\": \"Jacky Warren\",\n" +
"            \"nightSupport\": false,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-23T12:00:00Z\",\n" +
"          \"end\": \"2016-12-24T12:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 8,\n" +
"      \"worker\": {\n" +
"        \"id\": 41,\n" +
"        \"workerId\": 41,\n" +
"        \"name\": \"Jacky Warren\",\n" +
"        \"nightSupport\": false,\n" +
"        \"hasCar\": true,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": false,\n" +
"        \"religiousObservationSupport\": true,\n" +
"        \"hasDrivingLicense\": true\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 7,\n" +
"        \"shiftId\": 7,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 12,\n" +
"        \"maxWeekHours\": 35,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 48,\n" +
"          \"clientId\": 48,\n" +
"          \"name\": \"Hazard Chelsea\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 8,\n" +
"            \"requiresNightWork\": false,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": false,\n" +
"            \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              \n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 41,\n" +
"            \"workerId\": 41,\n" +
"            \"name\": \"Jacky Warren\",\n" +
"            \"nightSupport\": false,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-23T12:00:00Z\",\n" +
"          \"end\": \"2016-12-24T12:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 9,\n" +
"      \"worker\": {\n" +
"        \"id\": 45,\n" +
"        \"workerId\": 45,\n" +
"        \"name\": \"Jose Howard\",\n" +
"        \"nightSupport\": null,\n" +
"        \"hasCar\": null,\n" +
"        \"criminalRecordsChecked\": null,\n" +
"        \"weekendSupport\": null,\n" +
"        \"religiousObservationSupport\": null,\n" +
"        \"hasDrivingLicense\": null\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 11,\n" +
"        \"shiftId\": 11,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 12,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 35,\n" +
"          \"clientId\": 35,\n" +
"          \"name\": \"Felo Cheru\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 2,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": false,\n" +
"            \"requiresSupportOwnCar\": false,\n" +
"            \"requiresCriminalRecordsCheck\": true,\n" +
"            \"shiftType\": \"REGULAR_UNFIXED\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              {\n" +
"                \"id\": 2,\n" +
"                \"skillLevelId\": 2\n" +
"              }\n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 45,\n" +
"            \"workerId\": 45,\n" +
"            \"name\": \"Jose Howard\",\n" +
"            \"nightSupport\": null,\n" +
"            \"hasCar\": null,\n" +
"            \"criminalRecordsChecked\": null,\n" +
"            \"weekendSupport\": null,\n" +
"            \"religiousObservationSupport\": null,\n" +
"            \"hasDrivingLicense\": null\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-20T07:00:53Z\",\n" +
"          \"end\": \"2016-12-20T18:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 10,\n" +
"      \"worker\": {\n" +
"        \"id\": 45,\n" +
"        \"workerId\": 45,\n" +
"        \"name\": \"Jose Howard\",\n" +
"        \"nightSupport\": null,\n" +
"        \"hasCar\": null,\n" +
"        \"criminalRecordsChecked\": null,\n" +
"        \"weekendSupport\": null,\n" +
"        \"religiousObservationSupport\": null,\n" +
"        \"hasDrivingLicense\": null\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 1,\n" +
"        \"shiftId\": 1,\n" +
"        \"handOverTime\": 30,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 34,\n" +
"          \"clientId\": 34,\n" +
"          \"name\": \"John Doe\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 1,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": true,\n" +
"            \"shiftType\": \"FIXED_INFLEXIBLE\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"skillLevelId\": 1\n" +
"              },\n" +
"              {\n" +
"                \"id\": 3,\n" +
"                \"skillLevelId\": 3\n" +
"              }\n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              {\n" +
"                \"id\": 1,\n" +
"                \"activityId\": 1\n" +
"              }\n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 38,\n" +
"            \"workerId\": 38,\n" +
"            \"name\": \"Mary Ann\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": true,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": false,\n" +
"            \"religiousObservationSupport\": true,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 40,\n" +
"            \"workerId\": 40,\n" +
"            \"name\": \"Lucy Pat\",\n" +
"            \"nightSupport\": true,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": false,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": true\n" +
"          },\n" +
"          {\n" +
"            \"id\": 45,\n" +
"            \"workerId\": 45,\n" +
"            \"name\": \"Jose Howard\",\n" +
"            \"nightSupport\": null,\n" +
"            \"hasCar\": null,\n" +
"            \"criminalRecordsChecked\": null,\n" +
"            \"weekendSupport\": null,\n" +
"            \"religiousObservationSupport\": null,\n" +
"            \"hasDrivingLicense\": null\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-17T15:13:52Z\",\n" +
"          \"end\": \"2016-12-17T00:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    },\n" +
"    {\n" +
"      \"id\": 11,\n" +
"      \"worker\": {\n" +
"        \"id\": 43,\n" +
"        \"workerId\": 43,\n" +
"        \"name\": \"James Bond\",\n" +
"        \"nightSupport\": false,\n" +
"        \"hasCar\": false,\n" +
"        \"criminalRecordsChecked\": true,\n" +
"        \"weekendSupport\": true,\n" +
"        \"religiousObservationSupport\": false,\n" +
"        \"hasDrivingLicense\": false\n" +
"      },\n" +
"      \"shift\": {\n" +
"        \"id\": 13,\n" +
"        \"shiftId\": 13,\n" +
"        \"handOverTime\": 35,\n" +
"        \"minDayHours\": 4,\n" +
"        \"maxDayHours\": 8,\n" +
"        \"maxWeekHours\": 40,\n" +
"        \"status\": \"PENDING\",\n" +
"        \"client\": {\n" +
"          \"id\": 37,\n" +
"          \"clientId\": 37,\n" +
"          \"name\": \"Leo Mambo\",\n" +
"          \"shiftRequirements\": {\n" +
"            \"id\": 3,\n" +
"            \"requiresNightWork\": true,\n" +
"            \"requiresReligiousObservation\": true,\n" +
"            \"requiresSupportOwnCar\": true,\n" +
"            \"requiresCriminalRecordsCheck\": false,\n" +
"            \"shiftType\": \"FIXED_WITH_HANDOVER\",\n" +
"            \"requiredSupportSkillLevels\": [\n" +
"              \n" +
"            ],\n" +
"            \"requiredSupportActivities\": [\n" +
"              \n" +
"            ],\n" +
"            \"compatibleSupport\": [\n" +
"              \n" +
"            ]\n" +
"          }\n" +
"        },\n" +
"        \"workers\": [\n" +
"          {\n" +
"            \"id\": 43,\n" +
"            \"workerId\": 43,\n" +
"            \"name\": \"James Bond\",\n" +
"            \"nightSupport\": false,\n" +
"            \"hasCar\": false,\n" +
"            \"criminalRecordsChecked\": true,\n" +
"            \"weekendSupport\": true,\n" +
"            \"religiousObservationSupport\": false,\n" +
"            \"hasDrivingLicense\": false\n" +
"          }\n" +
"        ],\n" +
"        \"shiftDate\": {\n" +
"          \"start\": \"2016-12-24T06:00:00Z\",\n" +
"          \"end\": \"2016-12-24T12:00:00Z\"\n" +
"        }\n" +
"      },\n" +
"      \"status\": \"PENDING\"\n" +
"    }\n" +
"  ],\n" +
"  \"schedulerParameterization\": {\n" +
"    \"firstShiftDate\": {\n" +
"      \"start\": \"2016-12-17T15:13:52Z\",\n" +
"      \"end\": \"2016-12-17T00:00:00Z\"\n" +
"    },\n" +
"    \"planningWindowStart\": {\n" +
"      \"start\": \"2016-12-17T15:13:52Z\",\n" +
"      \"end\": \"2016-12-17T00:00:00Z\"\n" +
"    },\n" +
"    \"lastShiftDate\": {\n" +
"      \"start\": \"2016-12-21T00:00:00Z\",\n" +
"      \"end\": \"2016-12-22T06:00:00Z\"\n" +
"    }\n" +
"  }\n" +
"}";

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SolverFactory<ShiftSchedule> solverFactory = SolverFactory.createFromXmlResource("io/carer/scheduler/solverConfig.xml");

        Solver<ShiftSchedule> solver = solverFactory.buildSolver();
//        solver.addEventListener(new SolverEventListener<ShiftSchedule>() {
//            public void bestSolutionChanged(BestSolutionChangedEvent<ShiftSchedule> event) {
//                final ShiftSchedule schedule = event.getNewBestSolution();
//                try {
//                    // Try to update when we have found a new best solution solution
//                    System.out.println("The now solution: " + OBJECT_MAPPER.writeValueAsString(schedule));
//                } catch (JsonProcessingException ex) {
//                    // propagate this solution so we can find out what went wrong
//                    throw Throwables.propagate(ex);
//                }
//            }
//        });
        ShiftSchedule shiftSchedule = null;
        try {
            shiftSchedule = OBJECT_MAPPER.readValue(THE_PROBLEM, ShiftSchedule.class);
        } catch (IOException ex) {
            Logger.getLogger(ScheduleSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShiftSchedule solvedShifts = solver.solve(shiftSchedule);
        try {
            final String query = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(shiftSchedule);
            final String results = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(solvedShifts);
            System.out.println("Query: \n " + query);
            System.out.println("==========================================================================================================");
            System.out.println("Final Result \n" + results);
        } catch (JsonProcessingException ex) {
            throw Throwables.propagate(ex);
        }
    }

}
