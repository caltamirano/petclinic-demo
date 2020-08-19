import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class PetAppointmentList extends Component {

  constructor(props) {
    super(props);
    this.state = {vetDetails: undefined, appointments: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch(`/vets/${this.props.match.params.id}`)
      .then(response => response.json())
      .then(data => this.setState({vetDetails: data}));

    fetch(`/vets/${this.props.match.params.id}/appointments`)
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
    const {vetDetails, appointments, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const appointmentList = appointments.map(appointment => {
      return <tr key={appointment.id}>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.pet.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.pet.type.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.description}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.date}</td>
        <td style={{whiteSpace: 'nowrap'}}>{appointment.time}</td>
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
            <Button color="link" tag={Link} to="/vets">&lt; Back</Button>
          </div>          
          <h3 className="text-center">Appointments for Dr. {vetDetails.firstName + ' ' + vetDetails.lastName}</h3>
          <Table hover className="mt-4">
            <thead>
            <tr>
              <th>Pet Name</th>
              <th>Pet Type</th>
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

export default PetAppointmentList;