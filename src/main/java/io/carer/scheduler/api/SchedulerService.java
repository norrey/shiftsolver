package io.carer.scheduler.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import io.carer.scheduler.ShiftSchedule;
import javax.annotation.Nonnull;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.BasicConfigurator;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
@RequestScoped
@Path("/v1/scheduler")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SchedulerService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @POST
    @Nonnull
    public Response processSolution(@Nonnull final ShiftSchedule shiftSchedule) {
        requireNonNull(shiftSchedule, "The shiftSchedule must be specified");
        BasicConfigurator.configure();

        SolverFactory<ShiftSchedule> solverFactory = SolverFactory.createFromXmlResource("io/carer/scheduler/solverConfig.xml");
        Solver<ShiftSchedule> solver = solverFactory.buildSolver();
        solver.addEventListener((BestSolutionChangedEvent<ShiftSchedule> event) -> {
            final ShiftSchedule currentBestSolution = event.getNewBestSolution();
            updateCarer(currentBestSolution, shiftSchedule.getResponseUrl());
        });

        final ShiftSchedule problemSolution = solver.solve(shiftSchedule);
        problemSolution.setSolved(true);
        problemSolution.setTimeSpent(solver.getTimeMillisSpent());
        updateCarer(problemSolution, shiftSchedule.getResponseUrl());
        try {
            final String query = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(shiftSchedule);
            final String results = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(problemSolution);
            System.out.println("Problem : \n " + query);
            System.out.println("Solution : \n" + results);
        } catch (JsonProcessingException jsonProcessingException) {
            throw Throwables.propagate(jsonProcessingException);
        }

        return Response
                .status(Response.Status.OK)
                .entity(OBJECT_MAPPER.valueToTree(problemSolution))
                .type(MediaType.APPLICATION_JSON).build();
    }

    private void updateCarer(@Nonnull ShiftSchedule problemSolution, @Nonnull final String responseUrl) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(responseUrl)
                .addConverterFactory(JacksonConverterFactory.create(OBJECT_MAPPER))
                .build();
        final JsonApiInterface jsonApiInterface = retrofit.create(JsonApiInterface.class);
        final Call<SolutionUpdateResponse> call = jsonApiInterface.updateBestSolution(problemSolution);
        call.enqueue(new Callback<SolutionUpdateResponse>() {
            @Override
            public void onResponse(final Call<SolutionUpdateResponse> call, final retrofit2.Response<SolutionUpdateResponse> response) {
                // Check if status is ok and return everything 
            }

            @Override
            public void onFailure(final Call<SolutionUpdateResponse> call, final Throwable throwable) {
                throw Throwables.propagate(throwable);
            }
        });

    }

}
