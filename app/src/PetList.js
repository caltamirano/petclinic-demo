import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class PetList extends Component {

  constructor(props) {
    super(props);
    this.state = {pets: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/pets')
      .then(response => response.json())
      .then(data => this.setState({pets: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/pets/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedPets = [...this.state.pets].filter(i => i.id !== id);
      this.setState({pets: updatedPets});
    });
  }

  render() {
    const {pets, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const petList = pets.map(pet => {
      return <tr key={pet.id}>
        <td style={{whiteSpace: 'nowrap'}}>{pet.id}</td>
        <td style={{whiteSpace: 'nowrap'}}>{pet.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{pet.type.name}</td>
        <td style={{whiteSpace: 'nowrap'}}>{pet.birthDate}</td>
        <td>
          <ButtonGroup className="mr-1">
            <Button size="sm" color="primary" tag={Link} to={`/pets/${pet.id}/appointments`}>Appointments</Button>
            <Button size="sm" color="success" tag={Link} to={`/pets/${pet.id}`}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(pet.id)}>Delete</Button>            
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
          <div className="float-right">
            <Button color="success" tag={Link} to="/pets/new">Add Pet</Button>
          </div>
          <h3 className="text-center">Pets</h3>
          <Table hover className="mt-4">
            <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Type</th>
              <th>Date Of Birth</th>
              <th width="15%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {petList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default PetList;