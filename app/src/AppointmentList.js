import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class AppointmentList extends Component {

  constructor(props) {
    super(props);
    this.state = {appointments: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('appointments')
      .then(response => response.json())
      .then(data => this.setState({appointments: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/appointments/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedAppointments = [...this.state.appointments].filter(i => i.id !== id);
      this.setState({appointments: updatedAppointments});
    });
  }

  render() {
    const {appointments, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const appointmentList = appointments.map(appointment => {
      return <tr key={appointment.id}>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.id}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.vet.firstName + ' ' + appointment.vet.lastName}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.pet.name + ' (' + appointment.pet.type.name + ')'}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.description}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.date}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.time} hrs</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="danger" onClick={() => this.remove(appointment.id)}>Cancel</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-left">
            <Button color="link" tag={Link} to="/">&lt; Back</Button>
          </div>
          <h3 className="text-center">Appointments</h3>
          <Table hover className="mt-4">
            <thead>
            <tr>
              <th width="20%">Id</th>
              <th>Vet</th>
              <th>Pet</th>
              <th>Description</th>
              <th>Date</th>
              <th>Time</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {appointmentList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default AppointmentList;