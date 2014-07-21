package dejanpe.zadatak1.server.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dejanpe.zadatak1.server.command.commands.AddCommand;
import dejanpe.zadatak1.server.command.commands.CancelCommand;
import dejanpe.zadatak1.server.command.commands.ListAllCommand;
import dejanpe.zadatak1.server.command.commands.ListCommand;
import dejanpe.zadatak1.server.command.commands.ListPassengerCommand;
import dejanpe.zadatak1.server.command.commands.LoginCommand;
import dejanpe.zadatak1.server.command.commands.Logoff;
import dejanpe.zadatak1.server.command.commands.RegisterCommand;
import dejanpe.zadatak1.server.command.commands.ReserveCommand;
import dejanpe.zadatak1.server.command.commands.TopSecretCommand;
import dejanpe.zadatak1.server.core.flight.Flight;
import dejanpe.zadatak1.server.core.passenger.Passenger;
import dejanpe.zadatak1.server.core.passenger.PassengerDAO;

public enum CommandType {

	REGISTER("REGISTER") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String username = params[0];
			return new RegisterCommand(username);
		}
	},
	LOGIN("LOGIN") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String username = params[0];
			return new LoginCommand(username);
		}
	},
	LOGOFF("LOGOFF") {

		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 0) {
				return null;
			}
			return new Logoff();
		}

	},
	TOP_SECRET("TOP_SECRET") {
		@Override
		public Command buildCommand(final String[] params) {
			return new TopSecretCommand();
		}
	},
	ADD("ADD") {
		@Override
		public Command buildCommand(final String[] params) {
			// ADD <FLIGHT_ID> <DEPARTURE_DATE_TIME> <ARRIVAL_DATE_TIME>
			// <SOURCE> <DESTINATION> <NO_OF_PASSENGERS>
			if (params.length != 6) {
				return null;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			String flightId = params[0];
			Date departureTime;
			Date arrivalTime;
			try {
				departureTime = simpleDateFormat.parse(params[1]);
				arrivalTime = simpleDateFormat.parse(params[2]);
			} catch (ParseException e) {
				return new AddCommand();
			}
			
			String source = params[3];
			String destination = params[4];
			int numberOfPassingers = Integer.parseInt(params[5]);
			return new AddCommand(new Flight(flightId, departureTime, arrivalTime, source, destination,
					numberOfPassingers));
		}
	},
	LIST_ALL("LIST_ALL") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 0) {
				return null;
			}
			return new ListAllCommand();
		}
	},
	LIST("LIST") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String flightId = params[0];
			return new ListCommand(flightId);
		}
	},
	LIST_PASSENGER("LIST_PASSENGER") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String passengerJMBG = params[0];
			return new ListPassengerCommand(passengerJMBG);
		}
	},
	RESERVE("RESERVE") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 4) {
				return null;
			}
			String flightId = params[0];
			String JMBG = params[1];
			String name = params[2];
			String surname = params[3];
			// try to find passenger in cache
			Passenger passenger = PassengerDAO.get().getPassengerByJMBG(JMBG);
			if (passenger == null) {
				// if not found create new one
				passenger = new Passenger(JMBG, name, surname);
				PassengerDAO.get().insert(passenger);
			}
			return new ReserveCommand(flightId, passenger);
		}
	},
	CANCEL("CANCEL") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 2) {
				return null;
			}
			String flightId = params[0];
			String JMBG = params[1];
			return new CancelCommand(flightId, JMBG);
		}
	};

	private final String id;

	private CommandType(final String id) {
		this.id = id;
	}

	public abstract Command buildCommand(String[] params);

	public String getIdentifier() {
		return this.id;
	}
}
