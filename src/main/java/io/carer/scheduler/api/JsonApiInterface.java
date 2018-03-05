package io.carer.scheduler.api;

import io.carer.scheduler.ShiftSchedule;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public interface JsonApiInterface {

    @POST("./")
    Call<SolutionUpdateResponse> updateBestSolution(@Body final ShiftSchedule shiftSchedule);

}
