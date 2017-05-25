package controller;

import controller.action.Action;
import controller.action.AllMovieNameListAction;
import controller.action.CheckAccountBalanceAction;
import controller.action.EachScreenNameListAction;
import controller.action.InsertReservation;
import controller.action.ModifyFormMovieAction;
import controller.action.ModifyFormScreenAction;
import controller.action.ModifyFormTheaterAction;
import controller.action.ModifyScreenAction;
import controller.action.ModifyTheaterAction;
import controller.action.MovieGenreNameAllListAction;
import controller.action.MovieManufacturedCountryNameAllListAction;
import controller.action.MovieNameListAction;
import controller.action.PremiereMovieNameListAction;
import controller.action.RegistrationFormMovieAction;
import controller.action.RegistrationFormMovieGenreAction;
import controller.action.RegistrationFormMovieManufacturedCountryAction;
import controller.action.RegistrationFormScheduleAction;
import controller.action.RegistrationFormTicketTypeAction;
import controller.action.ReservationAction;
import controller.action.ScheduleCodeAction;
import controller.action.ScheduleViewingDayListAction;
import controller.action.ScheduleViewingStartTimeListAction;
import controller.action.ScreenInfoAction;
import controller.action.ScreenNameListAction;
import controller.action.ScreenSeatCountAction;
import controller.action.OneMovieAction;
import controller.action.TheaterInfoAction;
import controller.action.TheaterNameAllListAction;
import controller.action.TheaterNameListAction;
import controller.action.TicketTypeListAction;
import controller.action.TicketTypeMovieNameListAction;
import controller.action.TicketTypeScheduleViewingStartTimeListAction;
import controller.action.TicketTypeScreenAllListAction;
import controller.action.TicketTypeTheaterAllAction;
import controller.action.RegisterationFormTheaterAction;
import controller.action.RegisterationScheduleAction;
import controller.action.RegistrationMovieAction;
import controller.action.RegistrationMovieGenreAction;
import controller.action.RegistrationMovieManufacturedCountryAction;
import controller.action.RegistrationTheater;
import controller.action.RegistrationTicketTypeAction;

public class ActionFactory {
	
	private static ActionFactory instance;
	
	private ActionFactory(){}
	
	static ActionFactory getinstance(){
		
		if(instance == null) instance = new ActionFactory();
		
		return instance;
	}
	
	public Action getAction(String command){
		
		Action action = null;
		
		if(command.equals("")){
			
			action = new ReservationAction();
		}else if(command.equals("theater_list")){
			
			action = new TheaterNameListAction();
		}else if(command.equals("reservation_list")){
			
			action = new ReservationAction();
		}else if(command.equals("schedule_viewing_day_list")){
			
			action = new ScheduleViewingDayListAction();
		}else if(command.equals("movie_name_list")){
			
			action = new MovieNameListAction();
		}else if(command.equals("schedule_viewing_start_time_list")){
			
			action = new ScheduleViewingStartTimeListAction();
		}else if(command.equals("screen_name_list")){
			
			action = new ScreenNameListAction();
		}else if(command.equals("screen_seat_count")){
			
			action = new ScreenSeatCountAction();
		}else if(command.equals("schedule_code")){
			
			action = new ScheduleCodeAction();
		}else if(command.equals("insert_reservation")){
			
			action = new InsertReservation();
		}else if(command.equals("check_account_balance")){
			
			action = new CheckAccountBalanceAction();
		}else if(command.equals("registration_form_theater")){
			
			action = new RegisterationFormTheaterAction();
		}else if(command.equals("theater_all_list")){
			
			action = new TheaterNameAllListAction();
		}else if(command.equals("registration_theater")){
			
			action = new RegistrationTheater();
		}else if(command.equals("registration_form_movie")){
			
			action = new RegistrationFormMovieAction();
		}else if(command.equals("movie_genre_list")){
			
			action = new MovieGenreNameAllListAction();
		}else if(command.equals("movie_manufactured_country_list")){
			
			action = new MovieManufacturedCountryNameAllListAction();
		}else if(command.equals("registration_movie")){
			
			action = new RegistrationMovieAction();
		}else if(command.equals("registration_form_movie_genre")){
			
			action = new RegistrationFormMovieGenreAction();
		}else if(command.equals("registration_form_movie_manufactured_country")){
			
			action = new RegistrationFormMovieManufacturedCountryAction();
		}else if(command.equals("registration_movie_genre")){
			
			action = new RegistrationMovieGenreAction();
		}else if(command.equals("registration_movie_manufactured_country")){
			
			action = new RegistrationMovieManufacturedCountryAction();
		}else if(command.equals("registration_form_schedule")){
			
			action = new RegistrationFormScheduleAction();
		}else if(command.equals("each_screen_name_list")){
			
			action = new EachScreenNameListAction();
		}else if(command.equals("registration_schedule")){
			
			action = new RegisterationScheduleAction();
		}else if(command.equals("premiere_movie_name_list")){
			
			action = new PremiereMovieNameListAction();
		}else if(command.equals("registration_form_ticket_type")){
			
			action = new RegistrationFormTicketTypeAction();
		}else if(command.equals("ticket_type_movie_name_list")){
			
			action = new TicketTypeMovieNameListAction();
		}else if(command.equals("ticket_type_schedule_viewing_start_time_list")){
			
			action = new TicketTypeScheduleViewingStartTimeListAction();
		}else if(command.equals("ticket_type_theater_all_list")){
			
			action = new TicketTypeTheaterAllAction();
		}else if(command.equals("ticket_type_screen_name_list")){
			
			action = new TicketTypeScreenAllListAction();
		}else if(command.equals("registration_ticket_type")){
			
			action = new RegistrationTicketTypeAction();
		}else if(command.equals("ticket_type_list")){
			
			action = new TicketTypeListAction();
		}else if(command.equals("modify_form_theater")){
			
			action = new ModifyFormTheaterAction();
		}else if(command.equals("theater_info")){
			
			action = new TheaterInfoAction();
		}else if(command.equals("modify_theater")){
			
			action = new ModifyTheaterAction();
		}else if(command.equals("modify_form_screen")){
			
			action = new ModifyFormScreenAction();
		}else if(command.equals("screen_info")){
			
			action = new ScreenInfoAction();
		}else if(command.equals("modify_screen")){
			
			action = new ModifyScreenAction();
		}else if(command.equals("modify_form_movie")){
			
			action = new ModifyFormMovieAction();
		}else if(command.equals("select_one_movie")){
			
			action = new OneMovieAction();
		}else if(command.equals("all_movie_name_list")){
			
			action = new AllMovieNameListAction();
		}
		
		return action;
	}
}