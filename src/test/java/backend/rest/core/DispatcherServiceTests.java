package backend.rest.core;

import com.owlike.genson.Genson;
import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * (c) Tobias Fertig, FHWS 2017
 */
public class DispatcherServiceTests
{
	private final static String BASE_URL = "http://localhost:8080/api/";

	private Genson genson;

	private OkHttpClient client;


}
