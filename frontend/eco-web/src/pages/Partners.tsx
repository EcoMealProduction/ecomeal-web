
import React from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { Navbar } from '@/components/landing/Navbar';
import { Footer } from '@/components/landing/Footer';
import { TrendingUp, Users, Leaf, DollarSign, Clock, MapPin, Smartphone, BarChart } from 'lucide-react';

const Partners = () => {
  const benefits = [
    {
      icon: <TrendingUp className="w-8 h-8 text-green-600" />,
      title: "Creșterea Vânzărilor",
      description: "Atrage noi clienți și crește vânzările pentru produsele care ar fi fost aruncate"
    },
    {
      icon: <Users className="w-8 h-8 text-green-600" />,
      title: "Comunitate Angajată",
      description: "Conectează-te cu o comunitate de consumatori conștienți de mediu"
    },
    {
      icon: <Leaf className="w-8 h-8 text-green-600" />,
      title: "Impact Pozitiv",
      description: "Contribuie la reducerea risipei alimentare și protejarea mediului"
    },
    {
      icon: <DollarSign className="w-8 h-8 text-green-600" />,
      title: "Venituri Suplimentare",
      description: "Transformă produsele care expiră în venituri în loc să le arunci"
    }
  ];

  const features = [
    {
      icon: <Smartphone className="w-6 h-6 text-green-600" />,
      title: "Aplicație Simplă",
      description: "Interfață intuitivă pentru gestionarea ofertelor tale"
    },
    {
      icon: <Clock className="w-6 h-6 text-green-600" />,
      title: "Flexibilitate Totală",
      description: "Tu decizi când și ce produse să oferi cu reducere"
    },
    {
      icon: <MapPin className="w-6 h-6 text-green-600" />,
      title: "Vizibilitate Locală",
      description: "Clienții din apropiere îți găsesc ușor afacerea"
    },
    {
      icon: <BarChart className="w-6 h-6 text-green-600" />,
      title: "Statistici Detaliate",
      description: "Urmărește performanța și impactul tău asupra mediului"
    }
  ];

  const steps = [
    {
      number: "1",
      title: "Înscrie-te",
      description: "Completează formularul și ne vom conecta cu tine în 24 de ore"
    },
    {
      number: "2",
      title: "Configurare",
      description: "Te ajutăm să îți configurezi profilul și prima ofertă"
    },
    {
      number: "3",
      title: "Lansare",
      description: "Începi să vinzi produse și să reduci risipa alimentară"
    }
  ];

  return (
    <div className="min-h-screen bg-eco-bg">
      <Navbar />
      
      {/* Hero Section */}
      <section className="pt-24 pb-16 bg-gradient-to-br from-green-600 to-green-700 text-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-5xl font-bold mb-6">
              Devine Partener EcoMeal
            </h1>
            <p className="text-xl text-green-100 max-w-3xl mx-auto mb-8">
              Alătură-te misiunii noastre de reducere a risipei alimentare. Transformă produsele care expiră în venituri și contribuie la un viitor mai sustenabil.
            </p>
            <Button size="lg" className="bg-white text-green-600 hover:bg-green-50 text-lg px-8 py-4">
              Devino Partener Acum
            </Button>
          </div>
        </div>
      </section>

      {/* Benefits Section */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-4">
              De Ce Să Te Alături?
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              Descoperă avantajele de a fi partener EcoMeal și cum poți face diferența în comunitatea ta.
            </p>
          </div>

          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
            {benefits.map((benefit, index) => (
              <Card key={index} className="border-0 shadow-lg hover:shadow-xl transition-all duration-300">
                <CardContent className="p-8 text-center">
                  <div className="flex justify-center mb-4">
                    {benefit.icon}
                  </div>
                  <h3 className="text-xl font-bold text-gray-900 mb-3">
                    {benefit.title}
                  </h3>
                  <p className="text-gray-600">
                    {benefit.description}
                  </p>
                </CardContent>
              </Card>
            ))}
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 bg-eco-bg">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-4">
              Funcționalități pentru Parteneri
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              Platforma noastră îți oferă toate instrumentele necesare pentru a gestiona eficient ofertele tale.
            </p>
          </div>

          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((feature, index) => (
              <div key={index} className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-all duration-300">
                <div className="flex items-center mb-4">
                  {feature.icon}
                  <h3 className="text-lg font-semibold text-gray-900 ml-3">
                    {feature.title}
                  </h3>
                </div>
                <p className="text-gray-600">
                  {feature.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* How It Works */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-4">
              Cum Funcționează?
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              Procesul de înscriere este simplu și rapid. În doar 3 pași poți începe să reduci risipa alimentară.
            </p>
          </div>

          <div className="grid md:grid-cols-3 gap-12">
            {steps.map((step, index) => (
              <div key={index} className="text-center">
                <div className="w-16 h-16 bg-green-600 text-white rounded-full flex items-center justify-center text-2xl font-bold mx-auto mb-6">
                  {step.number}
                </div>
                <h3 className="text-2xl font-bold text-gray-900 mb-4">
                  {step.title}
                </h3>
                <p className="text-gray-600 text-lg">
                  {step.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 bg-gradient-to-br from-green-600 to-green-700 text-white">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h2 className="text-4xl font-bold mb-6">
            Gata să Începi?
          </h2>
          <p className="text-xl text-green-100 mb-8">
            Alătură-te comunității noastre de parteneri și contribuie la un viitor mai sustenabil. Completează formularul și te vom contacta în curând.
          </p>
          <div className="space-y-4 sm:space-y-0 sm:space-x-4 sm:flex sm:justify-center">
            <Button size="lg" className="bg-white text-green-600 hover:bg-green-50 text-lg px-8 py-4">
              Completează Formularul
            </Button>
            <Button size="lg" variant="outline" className="border-white text-white hover:bg-white hover:text-green-600 text-lg px-8 py-4">
              Contactează-ne
            </Button>
          </div>
        </div>
      </section>

      <Footer />
    </div>
  );
};

export default Partners;
