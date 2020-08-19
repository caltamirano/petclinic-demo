import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, Row, Col, Alert} from 'reactstrap';
import AppNavbar from './AppNavbar';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import 'react-day-picker/lib/style.css';
import moment from 'moment';
import {formatDate, parseDate} from 'react-day-picker/moment';

class PetAppointmentEdit extends Component {

  emptyAppointment = {
    "vet": {
        "id": "",
    },
    "pet": {
        "id": "",
    },
    "date": "",
    "time": "",
    "description": ""
};

  constructor(props) {
    super(props);
    this.state = {
      petDetails: null,
      appointment: this.emptyAppointment,
      vets: [],
      timeSlots: [],
      isLoading: true,
      alertVisible: false,
      errorMessage: ""
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleDayChange = this.handleDayChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.updateTimeSlots = this.updateTimeSlots.bind(this);
  }

  async componentDidMount() {
    this.setState({isLoading: true});

    const petDetails = await (await fetch(`/pets/${this.props.match.params.petId}`)).json()
    this.setState({petDetails});

    if (this.props.match.params.appointmentId !== 'new') {
      const appointment = await (await fetch(`/appointments/${this.props.match.params.appointmentId}`)).json();
      this.setState({appointment});
    } 

    const vets = await (await fetch('/vets')).json();
    this.setState({vets, isLoading: false});
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let appointment = {...this.state.appointment};

    if(name === 'vet') {
        appointment[name]['id'] = value;        
    } else {
        appointment[name] = value;
    }

    this.setState({appointment});

    if(name === 'vet' || name === 'date') {
      this.updateTimeSlots(appointment);
    }
  }

  handleDayChange(selectedDay, modifiers, dayPickerInput) {
    const input = dayPickerInput.getInput();
    let appointment = {...this.state.appointment};
    appointment.date = input.value;
    this.setState({appointment});
    this.updateTimeSlots(appointment);
  }

  updateTimeSlots(appointment) {
    if(appointment.vet.id && moment(appointment.date, "YYYY-MM-DD", true).isValid()) {
      fetch(`/vets/${appointment.vet.id}/slots/${appointment.date}`)
      .then(response => response.json())
      .then(timeSlots => this.setState({timeSlots}));
    } else {
      this.setState({timeSlots: []});
    }
    
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {appointment} = this.state;
    const {petDetails} = this.state;
    appointment.pet.id = petDetails.id;
    
    let response = await fetch('/appointments', {
      method: (appointment.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(appointment),
    }).then((response) => {
      if (!response.ok) {
        response.text().then((text) => {
          this.setState({errorMessage: text});          
        });
        throw new Error();  
      }
      return response;
    }).catch((error) => {
      this.setState({alertVisible: true});
    });

    if(response && response.ok) {
      this.props.history.push(`/pets/${petDetails.id}/appointments`);
    }
  }

  render() {
    const dateChanged = this.handleChange;
    const {petDetails, appointment, vets, timeSlots, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const title = <h2>{appointment.id ? 'Edit Appointment' : 'Add Appointment'}</h2>;
    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Alert color="danger" isOpen={this.state.alertVisible} toggle={(e) => this.setState({alertvisible: false})}>
          {this.state.errorMessage}
        </Alert>
        <Form onSubmit={this.handleSubmit}>          
          <FormGroup>
            <Label for="name">Pet</Label>
            <Input type="text" readOnly={true} value={petDetails.name} onChange={this.handleChange}/>
          </FormGroup>
          <FormGroup>
            <Label for="type">Vet</Label>
            <Input type="select" name="vet" id="vet" value={appointment.vet.id || ''} onChange={this.handleChange}>
              <option value=''>Choose an option</option>
              {vets.map((vet) => <option key={vet.id} value={vet.id}>{vet.firstName + ' ' + vet.lastName}</option>)}
            </Input>
          </FormGroup>
          <Row>
            <Col><FormGroup>
            <Label for="name">Date</Label>
            <DayPickerInput
                value={appointment.date}
                onDayChange={this.handleDayChange}
                dayPickerProps={{
                  selectedDays: moment(appointment.date).toDate(),
                  disabledDays: [{before: new Date()}, {daysOfWeek: [0, 6]}]
                }}
                format="YYYY-MM-DD"
                formatDate={formatDate}
                parseDate={parseDate}
                inputProps={{
                  className: "form-control",
                  onChange: event => dateChanged(event)
                }}
                placeholder="yyyy-MM-dd"
                style={{width: "100%"}}
              />
          </FormGroup></Col>
            <Col><FormGroup>
            <Label for="name">Time</Label>
            <Input type="select" name="time" id="time" value={appointment.time || ''} onChange={this.handleChange}>
              <option value=''>Choose an option</option>
              {timeSlots.map((timeSlot) => <option key={timeSlot} value={timeSlot}>{timeSlot}</option>)}
            </Input>
          </FormGroup></Col>
          </Row>
          <FormGroup>
            <Label for="name">Description</Label>
            <Input type="text" name="description" value={appointment.description} onChange={this.handleChange}/>
          </FormGroup> 
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to={`/pets/${petDetails.id}/appointments`}>Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(PetAppointmentEdit);